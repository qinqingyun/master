package com.meituan.food.web;

import com.meituan.food.mapper.DutyTablePOMapper;
import com.meituan.food.po.DutyTablePO;
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

    @GetMapping("/insert")
    public void insertOneGroupData(){

        DutyTablePO i=new DutyTablePO();
        i.setIsOnDuty(false);
        i.setDutyMis("yangchunxia");
        i.setDutyName("杨春霞");
        dutyTablePOMapper.insert(i);

        DutyTablePO po1=new DutyTablePO();
        po1.setIsOnDuty(false);
        po1.setDutyName("王兰");
        po1.setDutyMis("wanglan13");
        dutyTablePOMapper.insert(po1);

        DutyTablePO po2=new DutyTablePO();
        po2.setIsOnDuty(false);
        po2.setDutyName("郑翠红");
        po2.setDutyMis("zhengcuihong");
        dutyTablePOMapper.insert(po2);

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

        DutyTablePO po5=new DutyTablePO();
        po5.setIsOnDuty(false);
        po5.setDutyName("曹挡挡");
        po5.setDutyMis("caodangdang");
        dutyTablePOMapper.insert(po5);

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

        DutyTablePO r=new DutyTablePO();
        r.setIsOnDuty(false);
        r.setDutyMis("feilichao");
        r.setDutyName("费立超");
        dutyTablePOMapper.insert(r);

        DutyTablePO t=new DutyTablePO();
        t.setIsOnDuty(false);
        t.setDutyMis("liyuhua");
        t.setDutyName("李宇华");
        dutyTablePOMapper.insert(t);

        DutyTablePO u=new DutyTablePO();
        u.setIsOnDuty(false);
        u.setDutyMis("hanye03");
        u.setDutyName("韩晔");
        dutyTablePOMapper.insert(u);

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

        DutyTablePO x=new DutyTablePO();
        x.setDutyMis("guomengyao");
        x.setDutyName("郭孟瑶");
        x.setIsOnDuty(false);
        dutyTablePOMapper.insert(x);

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

        DutyTablePO ab=new DutyTablePO();
        ab.setIsOnDuty(false);
        ab.setDutyMis("chenhui18");
        ab.setDutyName("陈慧");
        dutyTablePOMapper.insert(ab);

        DutyTablePO k=new DutyTablePO();
        k.setIsOnDuty(false);
        k.setDutyMis("sunnaili");
        k.setDutyName("孙乃利");
        dutyTablePOMapper.insert(k);

        DutyTablePO c=new DutyTablePO();
        c.setIsOnDuty(false);
        c.setDutyName("贾晓琪");
        c.setDutyMis("jiaxiaoqi");
        dutyTablePOMapper.insert(c);

        DutyTablePO e=new DutyTablePO();
        e.setIsOnDuty(false);
        e.setDutyName("杨帅");
        e.setDutyMis("yangshuai07");
        dutyTablePOMapper.insert(e);

        DutyTablePO f=new DutyTablePO();
        f.setIsOnDuty(false);
        f.setDutyMis("konghongwei");
        f.setDutyName("孔宏伟");
        dutyTablePOMapper.insert(f);

        DutyTablePO h1=new DutyTablePO();
        h1.setIsOnDuty(false);
        h1.setDutyMis("huangguilin");
        h1.setDutyName("黄桂琳");
        dutyTablePOMapper.insert(h1);

        DutyTablePO h=new DutyTablePO();
        h.setIsOnDuty(false);
        h.setDutyMis("qinqingyun");
        h.setDutyName("秦庆贇");
        dutyTablePOMapper.insert(h);

        DutyTablePO ac=new DutyTablePO();
        ac.setIsOnDuty(false);
        ac.setDutyMis("wuqifang");
        ac.setDutyName("吴启芳");
        dutyTablePOMapper.insert(ac);

    }

}
