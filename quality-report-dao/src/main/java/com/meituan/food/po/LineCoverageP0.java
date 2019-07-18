package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LineCoverageP0 implements Serializable {
    private Integer id;

    private String srv;

    private String releaseName;

    private Integer coreLineC;

    private Integer coreLineM;

    private Integer coreLineTotal;

    private BigDecimal coreLineCoverage;

    private String coreLineCInterface;

    private Integer totalLineC;

    private Integer totalLineM;

    private Integer totalLineTotal;

    private BigDecimal totalLineCoverage;

    private String totalLineCInterface;

    private Integer departmentId;

    private String departmentName;

    private Integer departmentId2;

    private String departmentName2;

    private Date createdTime;

    private Date updateTime;

    private String errorMessage;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrv() {
        return srv;
    }

    public void setSrv(String srv) {
        this.srv = srv == null ? null : srv.trim();
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName == null ? null : releaseName.trim();
    }

    public Integer getCoreLineC() {
        return coreLineC;
    }

    public void setCoreLineC(Integer coreLineC) {
        this.coreLineC = coreLineC;
    }

    public Integer getCoreLineM() {
        return coreLineM;
    }

    public void setCoreLineM(Integer coreLineM) {
        this.coreLineM = coreLineM;
    }

    public Integer getCoreLineTotal() {
        return coreLineTotal;
    }

    public void setCoreLineTotal(Integer coreLineTotal) {
        this.coreLineTotal = coreLineTotal;
    }

    public BigDecimal getCoreLineCoverage() {
        return coreLineCoverage;
    }

    public void setCoreLineCoverage(BigDecimal coreLineCoverage) {
        this.coreLineCoverage = coreLineCoverage;
    }

    public String getCoreLineCInterface() {
        return coreLineCInterface;
    }

    public void setCoreLineCInterface(String coreLineCInterface) {
        this.coreLineCInterface = coreLineCInterface == null ? null : coreLineCInterface.trim();
    }

    public Integer getTotalLineC() {
        return totalLineC;
    }

    public void setTotalLineC(Integer totalLineC) {
        this.totalLineC = totalLineC;
    }

    public Integer getTotalLineM() {
        return totalLineM;
    }

    public void setTotalLineM(Integer totalLineM) {
        this.totalLineM = totalLineM;
    }

    public Integer getTotalLineTotal() {
        return totalLineTotal;
    }

    public void setTotalLineTotal(Integer totalLineTotal) {
        this.totalLineTotal = totalLineTotal;
    }

    public BigDecimal getTotalLineCoverage() {
        return totalLineCoverage;
    }

    public void setTotalLineCoverage(BigDecimal totalLineCoverage) {
        this.totalLineCoverage = totalLineCoverage;
    }

    public String getTotalLineCInterface() {
        return totalLineCInterface;
    }

    public void setTotalLineCInterface(String totalLineCInterface) {
        this.totalLineCInterface = totalLineCInterface == null ? null : totalLineCInterface.trim();
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

    public Integer getDepartmentId2() {
        return departmentId2;
    }

    public void setDepartmentId2(Integer departmentId2) {
        this.departmentId2 = departmentId2;
    }

    public String getDepartmentName2() {
        return departmentName2;
    }

    public void setDepartmentName2(String departmentName2) {
        this.departmentName2 = departmentName2 == null ? null : departmentName2.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
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
        LineCoverageP0 other = (LineCoverageP0) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSrv() == null ? other.getSrv() == null : this.getSrv().equals(other.getSrv()))
            && (this.getReleaseName() == null ? other.getReleaseName() == null : this.getReleaseName().equals(other.getReleaseName()))
            && (this.getCoreLineC() == null ? other.getCoreLineC() == null : this.getCoreLineC().equals(other.getCoreLineC()))
            && (this.getCoreLineM() == null ? other.getCoreLineM() == null : this.getCoreLineM().equals(other.getCoreLineM()))
            && (this.getCoreLineTotal() == null ? other.getCoreLineTotal() == null : this.getCoreLineTotal().equals(other.getCoreLineTotal()))
            && (this.getCoreLineCoverage() == null ? other.getCoreLineCoverage() == null : this.getCoreLineCoverage().equals(other.getCoreLineCoverage()))
            && (this.getCoreLineCInterface() == null ? other.getCoreLineCInterface() == null : this.getCoreLineCInterface().equals(other.getCoreLineCInterface()))
            && (this.getTotalLineC() == null ? other.getTotalLineC() == null : this.getTotalLineC().equals(other.getTotalLineC()))
            && (this.getTotalLineM() == null ? other.getTotalLineM() == null : this.getTotalLineM().equals(other.getTotalLineM()))
            && (this.getTotalLineTotal() == null ? other.getTotalLineTotal() == null : this.getTotalLineTotal().equals(other.getTotalLineTotal()))
            && (this.getTotalLineCoverage() == null ? other.getTotalLineCoverage() == null : this.getTotalLineCoverage().equals(other.getTotalLineCoverage()))
            && (this.getTotalLineCInterface() == null ? other.getTotalLineCInterface() == null : this.getTotalLineCInterface().equals(other.getTotalLineCInterface()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getDepartmentName() == null ? other.getDepartmentName() == null : this.getDepartmentName().equals(other.getDepartmentName()))
            && (this.getDepartmentId2() == null ? other.getDepartmentId2() == null : this.getDepartmentId2().equals(other.getDepartmentId2()))
            && (this.getDepartmentName2() == null ? other.getDepartmentName2() == null : this.getDepartmentName2().equals(other.getDepartmentName2()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getErrorMessage() == null ? other.getErrorMessage() == null : this.getErrorMessage().equals(other.getErrorMessage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSrv() == null) ? 0 : getSrv().hashCode());
        result = prime * result + ((getReleaseName() == null) ? 0 : getReleaseName().hashCode());
        result = prime * result + ((getCoreLineC() == null) ? 0 : getCoreLineC().hashCode());
        result = prime * result + ((getCoreLineM() == null) ? 0 : getCoreLineM().hashCode());
        result = prime * result + ((getCoreLineTotal() == null) ? 0 : getCoreLineTotal().hashCode());
        result = prime * result + ((getCoreLineCoverage() == null) ? 0 : getCoreLineCoverage().hashCode());
        result = prime * result + ((getCoreLineCInterface() == null) ? 0 : getCoreLineCInterface().hashCode());
        result = prime * result + ((getTotalLineC() == null) ? 0 : getTotalLineC().hashCode());
        result = prime * result + ((getTotalLineM() == null) ? 0 : getTotalLineM().hashCode());
        result = prime * result + ((getTotalLineTotal() == null) ? 0 : getTotalLineTotal().hashCode());
        result = prime * result + ((getTotalLineCoverage() == null) ? 0 : getTotalLineCoverage().hashCode());
        result = prime * result + ((getTotalLineCInterface() == null) ? 0 : getTotalLineCInterface().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getDepartmentName() == null) ? 0 : getDepartmentName().hashCode());
        result = prime * result + ((getDepartmentId2() == null) ? 0 : getDepartmentId2().hashCode());
        result = prime * result + ((getDepartmentName2() == null) ? 0 : getDepartmentName2().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getErrorMessage() == null) ? 0 : getErrorMessage().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", srv=").append(srv);
        sb.append(", releaseName=").append(releaseName);
        sb.append(", coreLineC=").append(coreLineC);
        sb.append(", coreLineM=").append(coreLineM);
        sb.append(", coreLineTotal=").append(coreLineTotal);
        sb.append(", coreLineCoverage=").append(coreLineCoverage);
        sb.append(", coreLineCInterface=").append(coreLineCInterface);
        sb.append(", totalLineC=").append(totalLineC);
        sb.append(", totalLineM=").append(totalLineM);
        sb.append(", totalLineTotal=").append(totalLineTotal);
        sb.append(", totalLineCoverage=").append(totalLineCoverage);
        sb.append(", totalLineCInterface=").append(totalLineCInterface);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", departmentId2=").append(departmentId2);
        sb.append(", departmentName2=").append(departmentName2);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}