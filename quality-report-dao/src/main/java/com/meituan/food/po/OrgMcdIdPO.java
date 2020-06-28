package com.meituan.food.po;

import java.io.Serializable;

public class OrgMcdIdPO implements Serializable {
    private Integer id;

    private Integer orgId;

    private String orgName;

    private String mcdId;

    private Integer mcdName;

    private String childMcdId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getMcdId() {
        return mcdId;
    }

    public void setMcdId(String mcdId) {
        this.mcdId = mcdId;
    }

    public Integer getMcdName() {
        return mcdName;
    }

    public void setMcdName(Integer mcdName) {
        this.mcdName = mcdName;
    }

    public String getChildMcdId() {
        return childMcdId;
    }

    public void setChildMcdId(String childMcdId) {
        this.childMcdId = childMcdId;
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
        OrgMcdIdPO other = (OrgMcdIdPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getOrgName() == null ? other.getOrgName() == null : this.getOrgName().equals(other.getOrgName()))
            && (this.getMcdId() == null ? other.getMcdId() == null : this.getMcdId().equals(other.getMcdId()))
            && (this.getMcdName() == null ? other.getMcdName() == null : this.getMcdName().equals(other.getMcdName()))
            && (this.getChildMcdId() == null ? other.getChildMcdId() == null : this.getChildMcdId().equals(other.getChildMcdId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getOrgName() == null) ? 0 : getOrgName().hashCode());
        result = prime * result + ((getMcdId() == null) ? 0 : getMcdId().hashCode());
        result = prime * result + ((getMcdName() == null) ? 0 : getMcdName().hashCode());
        result = prime * result + ((getChildMcdId() == null) ? 0 : getChildMcdId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orgId=").append(orgId);
        sb.append(", orgName=").append(orgName);
        sb.append(", mcdId=").append(mcdId);
        sb.append(", mcdName=").append(mcdName);
        sb.append(", childMcdId=").append(childMcdId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}