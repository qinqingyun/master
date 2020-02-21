package com.meituan.food.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PipelineItPO implements Serializable {
    private Integer id;

    private Integer departmentId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getPipelineTotal() {
        return pipelineTotal;
    }

    public void setPipelineTotal(Integer pipelineTotal) {
        this.pipelineTotal = pipelineTotal;
    }

    public BigDecimal getPassRate() {
        return passRate;
    }

    public void setPassRate(BigDecimal passRate) {
        this.passRate = passRate;
    }

    public Integer getPipelinePass() {
        return pipelinePass;
    }

    public void setPipelinePass(Integer pipelinePass) {
        this.pipelinePass = pipelinePass;
    }

    public Integer getWholeCasePass() {
        return wholeCasePass;
    }

    public void setWholeCasePass(Integer wholeCasePass) {
        this.wholeCasePass = wholeCasePass;
    }

    public Integer getWholeCaseTotal() {
        return wholeCaseTotal;
    }

    public void setWholeCaseTotal(Integer wholeCaseTotal) {
        this.wholeCaseTotal = wholeCaseTotal;
    }

    public BigDecimal getCasePassRate() {
        return casePassRate;
    }

    public void setCasePassRate(BigDecimal casePassRate) {
        this.casePassRate = casePassRate;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    private String departmentName;

    private Integer pipelineTotal;

    private BigDecimal passRate;

    private Integer pipelinePass;

    private Integer wholeCasePass;

    private Integer wholeCaseTotal;

    private BigDecimal casePassRate;

    private LocalDate createTime;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}