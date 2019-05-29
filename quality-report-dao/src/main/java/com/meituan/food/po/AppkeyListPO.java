package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class AppkeyListPO implements Serializable {
    private Integer id;

    private String owt;

    private String pdl;

    private String srv;

    private String appkey;

    private Integer departmentId;

    private Integer offline;

    private Date createdTime;

    private Date updatedTime;

    private Integer rank;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwt() {
        return owt;
    }

    public void setOwt(String owt) {
        this.owt = owt == null ? null : owt.trim();
    }

    public String getPdl() {
        return pdl;
    }

    public void setPdl(String pdl) {
        this.pdl = pdl == null ? null : pdl.trim();
    }

    public String getSrv() {
        return srv;
    }

    public void setSrv(String srv) {
        this.srv = srv == null ? null : srv.trim();
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getOffline() {
        return offline;
    }

    public void setOffline(Integer offline) {
        this.offline = offline;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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
        AppkeyListPO other = (AppkeyListPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwt() == null ? other.getOwt() == null : this.getOwt().equals(other.getOwt()))
            && (this.getPdl() == null ? other.getPdl() == null : this.getPdl().equals(other.getPdl()))
            && (this.getSrv() == null ? other.getSrv() == null : this.getSrv().equals(other.getSrv()))
            && (this.getAppkey() == null ? other.getAppkey() == null : this.getAppkey().equals(other.getAppkey()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getOffline() == null ? other.getOffline() == null : this.getOffline().equals(other.getOffline()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()))
            && (this.getRank() == null ? other.getRank() == null : this.getRank().equals(other.getRank()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwt() == null) ? 0 : getOwt().hashCode());
        result = prime * result + ((getPdl() == null) ? 0 : getPdl().hashCode());
        result = prime * result + ((getSrv() == null) ? 0 : getSrv().hashCode());
        result = prime * result + ((getAppkey() == null) ? 0 : getAppkey().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getOffline() == null) ? 0 : getOffline().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        result = prime * result + ((getRank() == null) ? 0 : getRank().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", owt=").append(owt);
        sb.append(", pdl=").append(pdl);
        sb.append(", srv=").append(srv);
        sb.append(", appkey=").append(appkey);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", offline=").append(offline);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", rank=").append(rank);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}