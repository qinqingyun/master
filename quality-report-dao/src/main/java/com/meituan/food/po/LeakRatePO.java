package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LeakRatePO implements Serializable {
    private Integer id;

    private Integer issueNum;

    private Integer bugNum;

    private BigDecimal leakTestRate;

    private String month;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(Integer issueNum) {
        this.issueNum = issueNum;
    }

    public Integer getBugNum() {
        return bugNum;
    }

    public void setBugNum(Integer bugNum) {
        this.bugNum = bugNum;
    }

    public BigDecimal getLeakTestRate() {
        return leakTestRate;
    }

    public void setLeakTestRate(BigDecimal leakTestRate) {
        this.leakTestRate = leakTestRate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
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
        LeakRatePO other = (LeakRatePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIssueNum() == null ? other.getIssueNum() == null : this.getIssueNum().equals(other.getIssueNum()))
            && (this.getBugNum() == null ? other.getBugNum() == null : this.getBugNum().equals(other.getBugNum()))
            && (this.getLeakTestRate() == null ? other.getLeakTestRate() == null : this.getLeakTestRate().equals(other.getLeakTestRate()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssueNum() == null) ? 0 : getIssueNum().hashCode());
        result = prime * result + ((getBugNum() == null) ? 0 : getBugNum().hashCode());
        result = prime * result + ((getLeakTestRate() == null) ? 0 : getLeakTestRate().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
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
        sb.append(", issueNum=").append(issueNum);
        sb.append(", bugNum=").append(bugNum);
        sb.append(", leakTestRate=").append(leakTestRate);
        sb.append(", month=").append(month);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}