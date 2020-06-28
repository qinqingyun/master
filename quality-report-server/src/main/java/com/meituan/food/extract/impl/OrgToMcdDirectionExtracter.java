package com.meituan.food.extract.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.meituan.food.mapper.McdInfoPOMapper;
import com.meituan.food.mapper.OrgMcdIdPOMapper;
import com.meituan.food.po.McdInfoPO;
import com.meituan.food.po.OrgMcdIdPO;
import com.meituan.food.utils.HttpUtils;
import com.sankuai.meituan.org.openapi.model.Hierarchy;
import com.sankuai.meituan.org.opensdk.model.domain.Org;
import com.sankuai.meituan.org.opensdk.model.domain.items.EmpItems;
import com.sankuai.meituan.org.opensdk.model.domain.items.OrgItems;
import com.sankuai.meituan.org.opensdk.service.EmpService;
import com.sankuai.meituan.org.opensdk.service.OrgService;
import com.sankuai.meituan.org.queryservice.domain.base.Paging;
import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;
import com.sankuai.meituan.org.treeservice.domain.EmpHierarchyCond;
import com.sankuai.meituan.org.treeservice.domain.param.OrgHierarchyCond;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrgToMcdDirectionExtracter {

    @Resource
    private EmpService empService;

    @Resource
    private OrgService orgService;

    @Resource
    private OrgMcdIdPOMapper orgMcdIdPOMapper;

    @Resource
    private McdInfoPOMapper mcdInfoPOMapper;

    private static final String mcdUrl="http://config-hotel.sankuai.com/mcd/org/basic?direction_id=1";

    public void getAllOrgName()throws MDMThriftException {

        EmpHierarchyCond empCond = new EmpHierarchyCond();
        empCond = empCond.jobStatusIdET(15);//在职
        Paging paging = new Paging();
        paging.setSize(1000);

        OrgHierarchyCond orgHierarchyCond=new OrgHierarchyCond();

//        EmpItems empItems = empService.queryEmp(orgId, 5, empCond, paging);
        OrgItems orgItems = orgService.queryBySuperior("112515", 0, orgHierarchyCond, paging);

        Hierarchy<Org> orgHierarchy = orgService.queryOrgTree("112515", 3, orgHierarchyCond);

     //   System.out.println(orgHierarchy.toString());

        List<Integer> allOrgId = orgMcdIdPOMapper.selectAllOrgId();

        orgItems.getItems().forEach((org) ->{
            OrgMcdIdPO po=new OrgMcdIdPO();
            String orgNamePath = org.getOrgNamePath();
            String orgId = org.getOrgId();
            String orgPath = orgNamePath.substring(orgNamePath.indexOf("IPH-") + 4).replaceAll("-","/");
            System.out.println(orgId+" "+orgPath);
            po.setOrgId(Integer.valueOf(orgId));
            po.setOrgName(orgPath);

            System.out.println(orgNamePath+" "+org.getOrgPath());
            if (allOrgId.contains(po.getOrgId())){
                OrgMcdIdPO po1 = orgMcdIdPOMapper.selectByOrgId(po.getOrgId());
                if (!po1.getOrgName().equals(orgPath)){
                    po1.setOrgName(orgPath);
                    orgMcdIdPOMapper.updateByPrimaryKey(po1);
                }
            }else {
                orgMcdIdPOMapper.insert(po);
            }

        });

       //     System.out.println(orgItems.toString());

    }

    public void getAllMCD(){

        List<McdInfoPO> mcdInfoPOS=new ArrayList<>();

        JSONObject mcdResp = HttpUtils.doGet(mcdUrl, JSONObject.class, ImmutableMap.of());
        JSONArray mcdArray = mcdResp.getJSONObject("info").getJSONArray("children");
        for (Object mcd : mcdArray) {
           if(((JSONObject)mcd).getString("label").equals("到店事业群")){
               JSONArray daodianMcd=((JSONObject)mcd).getJSONArray("children");
               for (Object d : daodianMcd) {
                   if (((JSONObject)d).getString("label").equals("平台技术部")){

                       /*父节点*/
                       McdInfoPO mcdInfoPO=new McdInfoPO();
                       int fatherDir=((JSONObject)d).getInteger("direction_id");
                       mcdInfoPO.setMcdId(fatherDir);
                       mcdInfoPO.setMcdName(((JSONObject)d).getString("value"));
                       mcdInfoPOS.add(mcdInfoPO);

                       boolean leaf=((JSONObject)d).getBoolean("if_leaf");

                       if (leaf==false){
                           /*子节点*/
                           JSONArray daodianArray=((JSONObject)d).getJSONArray("children");
                           iteratorTree(mcdInfoPOS,daodianArray,fatherDir);
                       }


                   }
               }
           }
        }

        for (McdInfoPO mcdInfoPO : mcdInfoPOS) {
            mcdInfoPOMapper.insert(mcdInfoPO);
        }

    }

    public List<McdInfoPO> iteratorTree(List<McdInfoPO> mcdInfoPOS,JSONArray mcdTree,int fatherDir){
        for (Object o : mcdTree) {
            McdInfoPO po=new McdInfoPO();
            Integer direction_id = ((JSONObject) o).getInteger("direction_id");
            po.setMcdId(direction_id);
            po.setMcdName( ((JSONObject) o).getString("value"));
            po.setMcdFatherId(fatherDir);

            mcdInfoPOS.add(po);

            boolean leaf=((JSONObject)o).getBoolean("if_leaf");
            if (leaf!=true){
                int father2=direction_id;
                iteratorTree(mcdInfoPOS,((JSONObject)o).getJSONArray("children"),father2);
            }
        }

        return mcdInfoPOS;
    }
}
