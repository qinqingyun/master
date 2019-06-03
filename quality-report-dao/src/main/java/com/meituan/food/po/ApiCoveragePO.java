package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApiCoveragePO implements Serializable {
    private Integer id;

    private String appkey;

    private String department;

    private Integer allApiNum;

    private Integer coverApiNum;

    private BigDecimal apiCoverage;

    private Integer departmentId;

    private Integer allCoreApiNum;

    private Integer coverCoreApiNum;

    private Date coverageDate;

    private Date createdTime;

    private BigDecimal coreApiCoverage;

    private Integer departmentId2;

    private String department2;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

    public Date getCoverageDate() {
        return coverageDate;
    }

    public void setCoverageDate(Date coverageDate) {
        this.coverageDate = coverageDate;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public BigDecimal getCoreApiCoverage() {
        return coreApiCoverage;
    }

    public void setCoreApiCoverage(BigDecimal coreApiCoverage) {
        this.coreApiCoverage = coreApiCoverage;
    }

    public Integer getDepartmentId2() {
        return departmentId2;
    }

    public void setDepartmentId2(Integer departmentId2) {
        this.departmentId2 = departmentId2;
    }

    public String getDepartment2() {
        return department2;
    }

    public void setDepartment2(String department2) {
        this.department2 = department2 == null ? null : department2.trim();
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
        ApiCoveragePO other = (ApiCoveragePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppkey() == null ? other.getAppkey() == null : this.getAppkey().equals(other.getAppkey()))
            && (this.getDepartment() == null ? other.getDepartment() == null : this.getDepartment().equals(other.getDepartment()))
            && (this.getAllApiNum() == null ? other.getAllApiNum() == null : this.getAllApiNum().equals(other.getAllApiNum()))
            && (this.getCoverApiNum() == null ? other.getCoverApiNum() == null : this.getCoverApiNum().equals(other.getCoverApiNum()))
            && (this.getApiCoverage() == null ? other.getApiCoverage() == null : this.getApiCoverage().equals(other.getApiCoverage()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getAllCoreApiNum() == null ? other.getAllCoreApiNum() == null : this.getAllCoreApiNum().equals(other.getAllCoreApiNum()))
            && (this.getCoverCoreApiNum() == null ? other.getCoverCoreApiNum() == null : this.getCoverCoreApiNum().equals(other.getCoverCoreApiNum()))
            && (this.getCoverageDate() == null ? other.getCoverageDate() == null : this.getCoverageDate().equals(other.getCoverageDate()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getCoreApiCoverage() == null ? other.getCoreApiCoverage() == null : this.getCoreApiCoverage().equals(other.getCoreApiCoverage()))
            && (this.getDepartmentId2() == null ? other.getDepartmentId2() == null : this.getDepartmentId2().equals(other.getDepartmentId2()))
            && (this.getDepartment2() == null ? other.getDepartment2() == null : this.getDepartment2().equals(other.getDepartment2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppkey() == null) ? 0 : getAppkey().hashCode());
        result = prime * result + ((getDepartment() == null) ? 0 : getDepartment().hashCode());
        result = prime * result + ((getAllApiNum() == null) ? 0 : getAllApiNum().hashCode());
        result = prime * result + ((getCoverApiNum() == null) ? 0 : getCoverApiNum().hashCode());
        result = prime * result + ((getApiCoverage() == null) ? 0 : getApiCoverage().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getAllCoreApiNum() == null) ? 0 : getAllCoreApiNum().hashCode());
        result = prime * result + ((getCoverCoreApiNum() == null) ? 0 : getCoverCoreApiNum().hashCode());
        result = prime * result + ((getCoverageDate() == null) ? 0 : getCoverageDate().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getCoreApiCoverage() == null) ? 0 : getCoreApiCoverage().hashCode());
        result = prime * result + ((getDepartmentId2() == null) ? 0 : getDepartmentId2().hashCode());
        result = prime * result + ((getDepartment2() == null) ? 0 : getDepartment2().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appkey=").append(appkey);
        sb.append(", department=").append(department);
        sb.append(", allApiNum=").append(allApiNum);
        sb.append(", coverApiNum=").append(coverApiNum);
        sb.append(", apiCoverage=").append(apiCoverage);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", allCoreApiNum=").append(allCoreApiNum);
        sb.append(", coverCoreApiNum=").append(coverCoreApiNum);
        sb.append(", coverageDate=").append(coverageDate);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", coreApiCoverage=").append(coreApiCoverage);
        sb.append(", departmentId2=").append(departmentId2);
        sb.append(", department2=").append(department2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}