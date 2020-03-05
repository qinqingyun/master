package com.meituan.food.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PipelineTpPO implements Serializable {
    Integer id;
    Integer direction_id;
    String direction_name;
    Date tp_date;

    Integer task;
    Integer sum;
    Integer pass;
    Integer failed;
    Integer oneTimePassCount;
    //自动化接入
    Integer autoRunCountNumberList;



    //分支规范检查
    Integer branchCheckOK = 0;
    Integer branchCheckNO = 0;
    //测试环境初始化
    Integer enviromentcheckOK = 0;
    Integer  enviromentcheckNO = 0;
    //代码冲突检查
    Integer codeCheckOK = 0;
    Integer codeCheckNO = 0;
    //静态代码检查
    Integer sonarCheckOK = 0;
    Integer sonarCheckNO = 0;
    //单元测试
    Integer unitCheckOk = 0;
    Integer unitCheckNO = 0;
    //部署
    Integer  buildCheckOK = 0;
    Integer buildCheckNO = 0;
    //自动化测试
    Integer autoCheckOK = 0;
    Integer autoCheckNO = 0;

    //其他原因
    Integer otherOK = 0;
    Integer otherNO = 0;

    //打回原因
    String rejectReasonString="";

    //打回描述
    String rejectDescString="";

    //打回原因
    List<String>  rejectReasons = new ArrayList();
    //打回描述
    List<String>  rejectDesc = new ArrayList();

    public List<String> getRejectReasons() {
        return rejectReasons;
    }

    public void setRejectReasons(List<String> rejectReasons) {
        this.rejectReasons = rejectReasons;
    }

    public List<String> getRejectDesc() {
        return rejectDesc;
    }

    public void setRejectDesc(List<String> rejectDesc) {
        this.rejectDesc = rejectDesc;
    }
    public void addRejectDesc(String rejectDesc) {
        this.rejectDesc.add(rejectDesc);
    }
    public void addRejectReasons(String rejectReasons) {
        this.rejectReasons.add(rejectReasons);
    }

}