package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DepartmentApiCoveragePO implements Serializable {
    private Integer id;

    private Integer departmentId;

    private String departmentName;

    private Integer allApiNum;

    private Integer coverApiNum;

    private BigDecimal apiCoverage;

    private Integer allCoreApiNum;

    private Integer coverCoreApiNum;

    private BigDecimal coreApiCoverage;

    private Date coverageDate;

    private Integer status;

    private Integer coreSrvApiNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getAllApiNum() {
        return allApiNum;
    }

    public void setAllApiNum(Integer allApiNum) {
        this.allApiNum = allApiNum;
    }

    public Integer getCoverApiNum() {
        return coverApiNum;
    }

    public void setCoverApiNum(Integer coverApiNum) {
        this.coverApiNum = coverApiNum;
    }

    public BigDecimal getApiCoverage() {
        return apiCoverage;
    }

    public void setApiCoverage(BigDecimal apiCoverage) {
        this.apiCoverage = apiCoverage;
    }

    public Integer getAllCoreApiNum() {
        return allCoreApiNum;
    }

    public void setAllCoreApiNum(Integer allCoreApiNum) {
        this.allCoreApiNum = allCoreApiNum;
    }

    public Integer getCoverCoreApiNum() {
        return coverCoreApiNum;
    }

    public void setCoverCoreApiNum(Integer coverCoreApiNum) {
        this.coverCoreApiNum = coverCoreApiNum;
    }

    public BigDecimal getCoreApiCoverage() {
        return coreApiCoverage;
    }

    public void setCoreApiCoverage(BigDecimal coreApiCoverage) {
        this.coreApiCoverage = coreApiCoverage;
    }

    public Date getCoverageDate() {
        return coverageDate;
    }

    public void setCoverageDate(Date coverageDate) {
        this.coverageDate = coverageDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCoreSrvApiNum() {
        return coreSrvApiNum;
    }

    public void setCoreSrvApiNum(Integer coreSrvApiNum) {
        this.coreSrvApiNum = coreSrvApiNum;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DepartmentApiCoveragePO other = (DepartmentApiCoveragePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getDepartmentName() == null ? other.getDepartmentName() == null : this.getDepartmentName().equals(other.getDepartmentName()))
            && (this.getAllApiNum() == null ? other.getAllApiNum() == null : this.getAllApiNum().equals(other.getAllApiNum()))
            && (this.getCoverApiNum() == null ? other.getCoverApiNum() == null : this.getCoverApiNum().equals(other.getCoverApiNum()))
            && (this.getApiCoverage() == null ? other.getApiCoverage() == null : this.getApiCoverage().equals(other.getApiCoverage()))
            && (this.getAllCoreApiNum() == null ? other.getAllCoreApiNum() == null : this.getAllCoreApiNum().equals(other.getAllCoreApiNum()))
            && (this.getCoverCoreApiNum() == null ? other.getCoverCoreApiNum() == null : this.getCoverCoreApiNum().equals(other.getCoverCoreApiNum()))
            && (this.getCoreApiCoverage() == null ? other.getCoreApiCoverage() == null : this.getCoreApiCoverage().equals(other.getCoreApiCoverage()))
            && (this.getCoverageDate() == null ? other.getCoverageDate() == null : this.getCoverageDate().equals(other.getCoverageDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCoreSrvApiNum() == null ? other.getCoreSrvApiNum() == null : this.getCoreSrvApiNum().equals(other.getCoreSrvApiNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getDepartmentName() == null) ? 0 : getDepartmentName().hashCode());
        result = prime * result + ((getAllApiNum() == null) ? 0 : getAllApiNum().hashCode());
        result = prime * result + ((getCoverApiNum() == null) ? 0 : getCoverApiNum().hashCode());
        result = prime * result + ((getApiCoverage() == null) ? 0 : getApiCoverage().hashCode());
        result = prime * result + ((getAllCoreApiNum() == null) ? 0 : getAllCoreApiNum().hashCode());
        result = prime * result + ((getCoverCoreApiNum() == null) ? 0 : getCoverCoreApiNum().hashCode());
        result = prime * result + ((getCoreApiCoverage() == null) ? 0 : getCoreApiCoverage().hashCode());
        result = prime * result + ((getCoverageDate() == null) ? 0 : getCoverageDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCoreSrvApiNum() == null) ? 0 : getCoreSrvApiNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", allApiNum=").append(allApiNum);
        sb.append(", coverApiNum=").append(coverApiNum);
        sb.append(", apiCoverage=").append(apiCoverage);
        sb.append(", allCoreApiNum=").append(allCoreApiNum);
        sb.append(", coverCoreApiNum=").append(coverCoreApiNum);
        sb.append(", coreApiCoverage=").append(coreApiCoverage);
        sb.append(", coverageDate=").append(coverageDate);
        sb.append(", status=").append(status);
        sb.append(", coreSrvApiNum=").append(coreSrvApiNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}