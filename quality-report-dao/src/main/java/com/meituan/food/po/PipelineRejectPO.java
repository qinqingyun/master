package com.meituan.food.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PipelineRejectPO implements Serializable {
    public Integer getBranchCheckOK() {
        return branchCheckOK;
    }

    public void setBranchCheckOK(Integer branchCheckOK) {
        this.branchCheckOK = branchCheckOK;
    }

    public Integer getBranchCheckNO() {
        return branchCheckNO;
    }

    public void setBranchCheckNO(Integer branchCheckNO) {
        this.branchCheckNO = branchCheckNO;
    }

    public Integer getEnviromentcheckOK() {
        return enviromentcheckOK;
    }

    public void setEnviromentcheckOK(Integer enviromentcheckOK) {
        this.enviromentcheckOK = enviromentcheckOK;
    }

    public Integer getEnviromentcheckNO() {
        return enviromentcheckNO;
    }

    public void setEnviromentcheckNO(Integer enviromentcheckNO) {
        this.enviromentcheckNO = enviromentcheckNO;
    }

    public Integer getCodeCheckOK() {
        return codeCheckOK;
    }

    public void setCodeCheckOK(Integer codeCheckOK) {
        this.codeCheckOK = codeCheckOK;
    }

    public Integer getCodeCheckNO() {
        return codeCheckNO;
    }

    public void setCodeCheckNO(Integer codeCheckNO) {
        this.codeCheckNO = codeCheckNO;
    }

    public Integer getSonarCheckOK() {
        return sonarCheckOK;
    }

    public void setSonarCheckOK(Integer sonarCheckOK) {
        this.sonarCheckOK = sonarCheckOK;
    }

    public Integer getSonarCheckNO() {
        return sonarCheckNO;
    }

    public void setSonarCheckNO(Integer sonarCheckNO) {
        this.sonarCheckNO = sonarCheckNO;
    }

    public Integer getUnitCheckOk() {
        return unitCheckOk;
    }

    public void setUnitCheckOk(Integer unitCheckOk) {
        this.unitCheckOk = unitCheckOk;
    }

    public Integer getUnitCheckNO() {
        return unitCheckNO;
    }

    public void setUnitCheckNO(Integer unitCheckNO) {
        this.unitCheckNO = unitCheckNO;
    }

    public Integer getBuildCheckOK() {
        return buildCheckOK;
    }

    public void setBuildCheckOK(Integer buildCheckOK) {
        this.buildCheckOK = buildCheckOK;
    }

    public Integer getBuildCheckNO() {
        return buildCheckNO;
    }

    public void setBuildCheckNO(Integer buildCheckNO) {
        this.buildCheckNO = buildCheckNO;
    }

    public Integer getAutoCheckOK() {
        return autoCheckOK;
    }

    public void setAutoCheckOK(Integer autoCheckOK) {
        this.autoCheckOK = autoCheckOK;
    }

    public Integer getAutoCheckNO() {
        return autoCheckNO;
    }

    public void setAutoCheckNO(Integer autoCheckNO) {
        this.autoCheckNO = autoCheckNO;
    }

    public Integer getOtherOK() {
        return otherOK;
    }

    public void setOtherOK(Integer otherOK) {
        this.otherOK = otherOK;
    }

    public Integer getOtherNO() {
        return otherNO;
    }

    public void setOtherNO(Integer otherNO) {
        this.otherNO = otherNO;
    }

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
    List<String>  rejectReasons = new ArrayList();
    //打回描述
    List<String>  rejectDesc = new ArrayList();

}