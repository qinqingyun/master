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
        if(department_id==256||department_id==258||department_id==259||department_id==257){
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

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    private Integer times;//执行PR次数

    private Integer pr_times;//执行自动化次数

    private Integer auto_times;//执行自动化总次数（new）

    public Integer getAuto_times() {
        return auto_times;
    }

    public void setAuto_times(Integer auto_times) {
        this.auto_times = auto_times;
    }

    public Integer getAuto_success_times() {
        return auto_success_times;
    }

    public void setAuto_success_times(Integer auto_success_times) {
        this.auto_success_times = auto_success_times;
    }

    private Integer auto_success_times;//执行自动化成功次数

    private String mis;


    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    private String coverage;

    private BigDecimal passes;

    private LocalDate auto_date;



}