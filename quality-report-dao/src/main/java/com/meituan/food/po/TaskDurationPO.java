package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TaskDurationPO implements Serializable {
    private Integer id;

    private String misid;

    private String realName;

    private String dashboard;

    private String startDate;

    private String endDate;

    private BigDecimal duration;

    private String orgGroup;

    private String orgId;

    private String firstLeader;

    private String secondLeader;

    private Date createdAt;

    private Boolean isnormal;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMisid() {
        return misid;
    }

    public void setMisid(String misid) {
        this.misid = misid == null ? null : misid.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getDashboard() {
        return dashboard;
    }

    public void setDashboard(String dashboard) {
        this.dashboard = dashboard == null ? null : dashboard.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getOrgGroup() {
        return orgGroup;
    }

    public void setOrgGroup(String orgGroup) {
        this.orgGroup = orgGroup == null ? null : orgGroup.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getFirstLeader() {
        return firstLeader;
    }

    public void setFirstLeader(String firstLeader) {
        this.firstLeader = firstLeader == null ? null : firstLeader.trim();
    }

    public String getSecondLeader() {
        return secondLeader;
    }

    public void setSecondLeader(String secondLeader) {
        this.secondLeader = secondLeader == null ? null : secondLeader.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getIsnormal() {
        return isnormal;
    }

    public void setIsnormal(Boolean isnormal) {
        this.isnormal = isnormal;
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
        TaskDurationPO other = (TaskDurationPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMisid() == null ? other.getMisid() == null : this.getMisid().equals(other.getMisid()))
            && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
            && (this.getDashboard() == null ? other.getDashboard() == null : this.getDashboard().equals(other.getDashboard()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getOrgGroup() == null ? other.getOrgGroup() == null : this.getOrgGroup().equals(other.getOrgGroup()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getFirstLeader() == null ? other.getFirstLeader() == null : this.getFirstLeader().equals(other.getFirstLeader()))
            && (this.getSecondLeader() == null ? other.getSecondLeader() == null : this.getSecondLeader().equals(other.getSecondLeader()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getIsnormal() == null ? other.getIsnormal() == null : this.getIsnormal().equals(other.getIsnormal()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMisid() == null) ? 0 : getMisid().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getDashboard() == null) ? 0 : getDashboard().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getOrgGroup() == null) ? 0 : getOrgGroup().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getFirstLeader() == null) ? 0 : getFirstLeader().hashCode());
        result = prime * result + ((getSecondLeader() == null) ? 0 : getSecondLeader().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getIsnormal() == null) ? 0 : getIsnormal().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", misid=").append(misid);
        sb.append(", realName=").append(realName);
        sb.append(", dashboard=").append(dashboard);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", duration=").append(duration);
        sb.append(", orgGroup=").append(orgGroup);
        sb.append(", orgId=").append(orgId);
        sb.append(", firstLeader=").append(firstLeader);
        sb.append(", secondLeader=").append(secondLeader);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", isnormal=").append(isnormal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}