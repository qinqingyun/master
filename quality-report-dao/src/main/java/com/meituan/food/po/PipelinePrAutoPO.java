package com.meituan.food.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PipelinePrAutoPO implements Serializable {
    private Integer id;

    private Integer department_id;
    private String group_name;

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(Integer department_id) {
        if(department_id==256||department_id==258||department_id==259||department_id==259){
            this.group_name="客户平台";
        }else if (department_id==251||department_id==252||department_id==241||department_id==217||department_id==497){
            this.group_name="到餐平台";
        }else if (department_id==253||department_id==254||department_id==255||department_id==296||department_id==321){
            this.group_name="C端服务端";
        }else if (department_id==260||department_id==261||department_id==262||department_id==265||department_id==264){
            this.group_name="商家平台";
        }else {
            this.group_name="其他";
        }
    }

    private String directionName;

    private String repo;
    private String priority;

    private Integer isAutoOn;

    private Integer totalCase;
    private Integer pr_times;



    public BigDecimal getCoverage() {
        return coverage;
    }

    public void setCoverage(BigDecimal coverage) {
        this.coverage = coverage;
    }

    private BigDecimal coverage;

    private BigDecimal passes;

    private LocalDate auto_date;



}