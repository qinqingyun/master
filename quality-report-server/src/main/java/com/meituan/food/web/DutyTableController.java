package com.meituan.food.web;

import com.meituan.food.mapper.DutyTablePOMapper;
import com.meituan.food.po.DutyTablePO;
import com.meituan.food.utils.DaXiangUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/duty")
public class DutyTableController {

    @Resource
    private DutyTablePOMapper dutyTablePOMapper;

    @GetMapping("delete")
    public void deleteDutyById(@RequestParam("id") int id){
        dutyTablePOMapper.deleteByPrimaryKey(id);
    }

    @GetMapping("update")
    public void updateDutyById(@RequestParam("id") int id){
        DutyTablePO po=new DutyTablePO();
        DutyTablePO dutyTablePO = dutyTablePOMapper.selectByPrimaryKey(id);
        po.setId(id);
        po.setIsOnDuty(false);
        po.setDutyMis(dutyTablePO.getDutyMis());
        po.setDutyName(dutyTablePO.getDutyName());
        dutyTablePOMapper.updateByPrimaryKey(po);
    }
//    @GetMapping("/push")
//    public void pushList(){
//        DaXiangUtils.pushToRoom("@黄桂琳 @秦庆贇 轮到你们值班了\n"+"周末值班QA须知\n" +
//                "1、客服商服反馈群，有问题反馈，会根据配置自动拉群处理，值班同学需关注群内的进度。\n" +
//                "2、若提报问题自动匹配错误，需值班同学手动拉相关人进群。若提报问题未自动匹配，则需要值班同学手动拉群处理，并将问题编号记录，同步给buyuqi同学\n" +
//                "3、值班同学需关注自己值班当天，群内问题的解决情况，若问题已解决，督促提报人关闭问题。\n" +
//                "4、值班同学可参考各平台业务对接人引导wiki：https://km.sankuai.com/page/181829982，拉取相关同学进群，配合解决问题。",879074L);
//
//        DaXiangUtils.pushToRoom("本周值班人员：" + "@黄桂琳 @秦庆贇",64013968876L );
//        DaXiangUtils.pushToPerson("本周值班人员：" + "黄桂琳 秦庆贇", "buyuqi");
//    }


    @GetMapping("/insert")
    public void insertOneGroupData(){

        DutyTablePO po5=new DutyTablePO();
        po5.setIsOnDuty(false);
        po5.setDutyName("费立超");
        po5.setDutyMis("feilichao");
        dutyTablePOMapper.insert(po5);

        DutyTablePO ac=new DutyTablePO();
        ac.setIsOnDuty(false);
        ac.setDutyMis("summer.sun");
        ac.setDutyName("孙蒙");
        dutyTablePOMapper.insert(ac);

        DutyTablePO po2=new DutyTablePO();
        po2.setIsOnDuty(false);
        po2.setDutyName("郭贝");
        po2.setDutyMis("bei.guo");
        dutyTablePOMapper.insert(po2);

        DutyTablePO x=new DutyTablePO();
        x.setDutyMis("周颖");
        x.setDutyName("zhouying09");
        x.setIsOnDuty(false);
        dutyTablePOMapper.insert(x);

        DutyTablePO ab=new DutyTablePO();
        ab.setIsOnDuty(false);
        ab.setDutyMis("余媛");
        ab.setDutyName("yuyuan");
        dutyTablePOMapper.insert(ab);

        DutyTablePO k=new DutyTablePO();
        k.setIsOnDuty(false);
        k.setDutyMis("孔敏");
        k.setDutyName("kongmin");
        dutyTablePOMapper.insert(k);

        DutyTablePO t=new DutyTablePO();
        t.setIsOnDuty(false);
        t.setDutyMis("于晓蝶");
        t.setDutyName("yuxiaodie");
        dutyTablePOMapper.insert(t);

        DutyTablePO r=new DutyTablePO();
        r.setIsOnDuty(false);
        r.setDutyMis("shuaiweiwei");
        r.setDutyName("帅维维");
        dutyTablePOMapper.insert(r);

        DutyTablePO h=new DutyTablePO();
        h.setIsOnDuty(false);
        h.setDutyMis("kelin03");
        h.setDutyName("柯琳");
        dutyTablePOMapper.insert(h);

        DutyTablePO po1=new DutyTablePO();
        po1.setIsOnDuty(false);
        po1.setDutyName("王兰");
        po1.setDutyMis("wanglan13");
        dutyTablePOMapper.insert(po1);

        DutyTablePO po3=new DutyTablePO();
        po3.setIsOnDuty(false);
        po3.setDutyName("刘松");
        po3.setDutyMis("liusong13");
        dutyTablePOMapper.insert(po3);

        DutyTablePO po4=new DutyTablePO();
        po4.setIsOnDuty(false);
        po4.setDutyName("曹东");
        po4.setDutyMis("caodong03");
        dutyTablePOMapper.insert(po4);

        DutyTablePO po6=new DutyTablePO();
        po6.setIsOnDuty(false);
        po6.setDutyName("陈思同");
        po6.setDutyMis("chensitong05");
        dutyTablePOMapper.insert(po6);

        DutyTablePO po7=new DutyTablePO();
        po7.setIsOnDuty(false);
        po7.setDutyName("全蓬琳");
        po7.setDutyMis("quanpenglin");
        dutyTablePOMapper.insert(po7);

        DutyTablePO po8=new DutyTablePO();
        po8.setIsOnDuty(false);
        po8.setDutyName("施颖竹");
        po8.setDutyMis("shiyingzhu");
        dutyTablePOMapper.insert(po8);

        DutyTablePO po9=new DutyTablePO();
        po9.setIsOnDuty(false);
        po9.setDutyName("张洋洋");
        po9.setDutyMis("zhangyangyang17");
        dutyTablePOMapper.insert(po9);

        DutyTablePO po10=new DutyTablePO();
        po10.setIsOnDuty(false);
        po10.setDutyName("甄玉敏");
        po10.setDutyMis("zhenyumin");
        dutyTablePOMapper.insert(po10);

        DutyTablePO l=new DutyTablePO();
        l.setIsOnDuty(false);
        l.setDutyName("王佳妮");
        l.setDutyMis("wangjiani03");
        dutyTablePOMapper.insert(l);

        DutyTablePO q=new DutyTablePO();
        q.setIsOnDuty(false);
        q.setDutyMis("wangjianming02");
        q.setDutyName("王坚铭");
        dutyTablePOMapper.insert(q);

        DutyTablePO w=new DutyTablePO();
        w.setIsOnDuty(false);
        w.setDutyMis("zhangyancui");
        w.setDutyName("张彦翠");
        dutyTablePOMapper.insert(w);

        DutyTablePO x1=new DutyTablePO();
        x1.setDutyMis("buyuqi");
        x1.setDutyName("部玉琪");
        x1.setIsOnDuty(false);
        dutyTablePOMapper.insert(x1);

        DutyTablePO y=new DutyTablePO();
        y.setIsOnDuty(false);
        y.setDutyName("熊一平");
        y.setDutyMis("xiongyiping");
        dutyTablePOMapper.insert(y);

        DutyTablePO z=new DutyTablePO();
        z.setIsOnDuty(false);
        z.setDutyMis("liuxiangyi");
        z.setDutyName("刘香怡");
        dutyTablePOMapper.insert(z);

        DutyTablePO aa=new DutyTablePO();
        aa.setIsOnDuty(false);
        aa.setDutyName("佟美娜");
        aa.setDutyMis("tongmeina");
        dutyTablePOMapper.insert(aa);

        DutyTablePO h1=new DutyTablePO();
        h1.setIsOnDuty(false);
        h1.setDutyMis("huangguilin");
        h1.setDutyName("黄桂琳");
        dutyTablePOMapper.insert(h1);

        DutyTablePO ad=new DutyTablePO();
        ad.setIsOnDuty(false);
        ad.setDutyMis("fanpanpan");
        ad.setDutyName("范盼盼");
        dutyTablePOMapper.insert(ad);

        DutyTablePO i=new DutyTablePO();
        i.setIsOnDuty(false);
        i.setDutyMis("yangchunxia");
        i.setDutyName("杨春霞");
        dutyTablePOMapper.insert(i);



    }

}
