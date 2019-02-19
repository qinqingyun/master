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

    @GetMapping("/insert")
    public void insertOneGroupData(){
        /*DutyTablePO a=new DutyTablePO();
        a.setDutyMis("niyang");
        a.setDutyName("倪阳");
        a.setIsOnDuty(false);
        dutyTablePOMapper.insert(a);

        DutyTablePO b=new DutyTablePO();
        b.setDutyMis("sunnaili");
        b.setDutyName("孙乃利");
        b.setIsOnDuty(false);
        dutyTablePOMapper.insert(b);

        DutyTablePO c=new DutyTablePO();
        c.setIsOnDuty(false);
        c.setDutyName("贾晓琪");
        c.setDutyMis("jiaxiaoqi");
        dutyTablePOMapper.insert(c);

        DutyTablePO d=new DutyTablePO();
        d.setDutyMis("wuhaibo");
        d.setDutyName("吴海波");
        d.setIsOnDuty(false);
        dutyTablePOMapper.insert(d);

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

        DutyTablePO g=new DutyTablePO();
        g.setIsOnDuty(false);
        g.setDutyMis("sunyu11");
        g.setDutyName("孙煜");
        dutyTablePOMapper.insert(g);

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

        DutyTablePO i=new DutyTablePO();
        i.setIsOnDuty(false);
        i.setDutyMis("yangchunxia");
        i.setDutyName("杨春霞");
        dutyTablePOMapper.insert(i);

        DutyTablePO j=new DutyTablePO();
        j.setIsOnDuty(false);
        j.setDutyName("张欣");
        j.setDutyMis("zhangxin49");
        dutyTablePOMapper.insert(j);

        DutyTablePO k=new DutyTablePO();
        k.setIsOnDuty(false);
        k.setDutyMis("yingzhixun");
        k.setDutyName("应直巡");
        dutyTablePOMapper.insert(k);

        DutyTablePO l=new DutyTablePO();
        l.setIsOnDuty(false);
        l.setDutyName("王佳妮");
        l.setDutyMis("wangjiani03");
        dutyTablePOMapper.insert(l);

        DutyTablePO m=new DutyTablePO();
        m.setIsOnDuty(false);
        m.setDutyMis("wangyu82");
        m.setDutyName("王宇");
        dutyTablePOMapper.insert(m);

        DutyTablePO n=new DutyTablePO();
        n.setIsOnDuty(false);
        n.setDutyName("张波");
        n.setDutyMis("zhangbo64");
        dutyTablePOMapper.insert(n);

        DutyTablePO o=new DutyTablePO();
        o.setIsOnDuty(false);
        o.setDutyMis("eqianyu");
        o.setDutyName("鄂乾宇");
        dutyTablePOMapper.insert(o);

        DutyTablePO p=new DutyTablePO();
        p.setIsOnDuty(false);
        p.setDutyName("张伟豪");
        p.setDutyMis("zhangweihao02");
        dutyTablePOMapper.insert(p);

        DutyTablePO q=new DutyTablePO();
        q.setIsOnDuty(false);
        q.setDutyMis("zhouke05");
        q.setDutyName("周克");
        dutyTablePOMapper.insert(q);

        DutyTablePO r=new DutyTablePO();
        r.setIsOnDuty(false);
        r.setDutyMis("feilichao");
        r.setDutyName("费立超");
        dutyTablePOMapper.insert(r);

        DutyTablePO s=new DutyTablePO();
        s.setIsOnDuty(false);
        s.setDutyMis("kongliang");
        s.setDutyName("孔亮");
        dutyTablePOMapper.insert(s);

        DutyTablePO t=new DutyTablePO();
        t.setIsOnDuty(false);
        t.setDutyMis("liyuhua");
        t.setDutyName("李宇华");
        dutyTablePOMapper.insert(t);

        DutyTablePO u=new DutyTablePO();
        u.setIsOnDuty(false);
        u.setDutyMis("wangyang60");
        u.setDutyName("王洋");
        dutyTablePOMapper.insert(u);

        DutyTablePO v=new DutyTablePO();
        v.setIsOnDuty(false);
        v.setDutyMis("fengjing04");
        v.setDutyName("冯静");
        dutyTablePOMapper.insert(v);

        DutyTablePO w=new DutyTablePO();
        w.setIsOnDuty(false);
        w.setDutyMis("zhangyancui");
        w.setDutyName("张彦翠");
        dutyTablePOMapper.insert(w);

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
        dutyTablePOMapper.insert(z);*/

       /* DutyTablePO aa=new DutyTablePO();
        aa.setIsOnDuty(false);
        aa.setDutyName("佟美娜");
        aa.setDutyMis("tongmeina");
        dutyTablePOMapper.insert(aa);

        DutyTablePO ab=new DutyTablePO();
        ab.setIsOnDuty(false);
        ab.setDutyMis("chenhui18");
        ab.setDutyName("陈慧");
        dutyTablePOMapper.insert(ab);*/
    }

}
