package com.meituan.food.po;

import java.io.Serializable;

public class Org2EmpDataPO implements Serializable {
    private Integer id;

    private String empid;

    private String name;

    private String mis;

    private String reportempid;

    private String reportempname;

    private String orgid;

    private String orgname;

    private String virtualreportempid;

    private String virtualreportempname;

    private String comment;

    private String updatedDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMis() {
        return mis;
    }

    public void setMis(String mis) {
        this.mis = mis == null ? null : mis.trim();
    }

    public String getReportempid() {
        return reportempid;
    }

    public void setReportempid(String reportempid) {
        this.reportempid = reportempid == null ? null : reportempid.trim();
    }

    public String getReportempname() {
        return reportempname;
    }

    public void setReportempname(String reportempname) {
        this.reportempname = reportempname == null ? null : reportempname.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getVirtualreportempid() {
        return virtualreportempid;
    }

    public void setVirtualreportempid(String virtualreportempid) {
        this.virtualreportempid = virtualreportempid == null ? null : virtualreportempid.trim();
    }

    public String getVirtualreportempname() {
        return virtualreportempname;
    }

    public void setVirtualreportempname(String virtualreportempname) {
        this.virtualreportempname = virtualreportempname == null ? null : virtualreportempname.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate == null ? null : updatedDate.trim();
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
        Org2EmpDataPO other = (Org2EmpDataPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEmpid() == null ? other.getEmpid() == null : this.getEmpid().equals(other.getEmpid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMis() == null ? other.getMis() == null : this.getMis().equals(other.getMis()))
            && (this.getReportempid() == null ? other.getReportempid() == null : this.getReportempid().equals(other.getReportempid()))
            && (this.getReportempname() == null ? other.getReportempname() == null : this.getReportempname().equals(other.getReportempname()))
            && (this.getOrgid() == null ? other.getOrgid() == null : this.getOrgid().equals(other.getOrgid()))
            && (this.getOrgname() == null ? other.getOrgname() == null : this.getOrgname().equals(other.getOrgname()))
            && (this.getVirtualreportempid() == null ? other.getVirtualreportempid() == null : this.getVirtualreportempid().equals(other.getVirtualreportempid()))
            && (this.getVirtualreportempname() == null ? other.getVirtualreportempname() == null : this.getVirtualreportempname().equals(other.getVirtualreportempname()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getUpdatedDate() == null ? other.getUpdatedDate() == null : this.getUpdatedDate().equals(other.getUpdatedDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEmpid() == null) ? 0 : getEmpid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMis() == null) ? 0 : getMis().hashCode());
        result = prime * result + ((getReportempid() == null) ? 0 : getReportempid().hashCode());
        result = prime * result + ((getReportempname() == null) ? 0 : getReportempname().hashCode());
        result = prime * result + ((getOrgid() == null) ? 0 : getOrgid().hashCode());
        result = prime * result + ((getOrgname() == null) ? 0 : getOrgname().hashCode());
        result = prime * result + ((getVirtualreportempid() == null) ? 0 : getVirtualreportempid().hashCode());
        result = prime * result + ((getVirtualreportempname() == null) ? 0 : getVirtualreportempname().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getUpdatedDate() == null) ? 0 : getUpdatedDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", empid=").append(empid);
        sb.append(", name=").append(name);
        sb.append(", mis=").append(mis);
        sb.append(", reportempid=").append(reportempid);
        sb.append(", reportempname=").append(reportempname);
        sb.append(", orgid=").append(orgid);
        sb.append(", orgname=").append(orgname);
        sb.append(", virtualreportempid=").append(virtualreportempid);
        sb.append(", virtualreportempname=").append(virtualreportempname);
        sb.append(", comment=").append(comment);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}