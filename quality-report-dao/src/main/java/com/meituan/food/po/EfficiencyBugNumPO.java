package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class EfficiencyBugNumPO implements Serializable {
    private Integer id;

    private String mis;

    private Integer createNum;

    private Integer acceptNum;

    private String efficiencyDate;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMis() {
        return mis;
    }

    public void setMis(String mis) {
        this.mis = mis == null ? null : mis.trim();
    }

    public Integer getCreateNum() {
        return createNum;
    }

    public void setCreateNum(Integer createNum) {
        this.createNum = createNum;
    }

    public Integer getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(Integer acceptNum) {
        this.acceptNum = acceptNum;
    }

    public String getEfficiencyDate() {
        return efficiencyDate;
    }

    public void setEfficiencyDate(String efficiencyDate) {
        this.efficiencyDate = efficiencyDate == null ? null : efficiencyDate.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        EfficiencyBugNumPO other = (EfficiencyBugNumPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMis() == null ? other.getMis() == null : this.getMis().equals(other.getMis()))
            && (this.getCreateNum() == null ? other.getCreateNum() == null : this.getCreateNum().equals(other.getCreateNum()))
            && (this.getAcceptNum() == null ? other.getAcceptNum() == null : this.getAcceptNum().equals(other.getAcceptNum()))
            && (this.getEfficiencyDate() == null ? other.getEfficiencyDate() == null : this.getEfficiencyDate().equals(other.getEfficiencyDate()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMis() == null) ? 0 : getMis().hashCode());
        result = prime * result + ((getCreateNum() == null) ? 0 : getCreateNum().hashCode());
        result = prime * result + ((getAcceptNum() == null) ? 0 : getAcceptNum().hashCode());
        result = prime * result + ((getEfficiencyDate() == null) ? 0 : getEfficiencyDate().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mis=").append(mis);
        sb.append(", createNum=").append(createNum);
        sb.append(", acceptNum=").append(acceptNum);
        sb.append(", efficiencyDate=").append(efficiencyDate);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}