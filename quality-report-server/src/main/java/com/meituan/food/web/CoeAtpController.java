package com.meituan.food.web;

import com.meituan.food.mapper.CoeAtpPOMapper;
import com.meituan.food.po.CoeAtpPO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/atp")
public class CoeAtpController {

    @Resource
    private CoeAtpPOMapper atpPOMapper;

    @GetMapping("/update")
    public String updateAtp(@RequestParam("coeId") int  coeId){
        CoeAtpPO po=new CoeAtpPO();
        po.setCoeId(coeId);
        po.setIsPush(true);
        atpPOMapper.insert(po);
        return "OK!";
    }

}
