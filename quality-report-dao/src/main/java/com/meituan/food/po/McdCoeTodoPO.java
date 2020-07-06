package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class McdCoeTodoPO implements Serializable {
    private Integer id;

    private Integer coeId;

    private String orgName;

    private Integer onesId;

    private String onesLink;

    private String onesTitle;

    private Boolean isFinish;

    private Boolean isDelay;

    private Date dealline;

    private String ownerMis;

    private String ownerName;

    private Date startDate;

    private Date actualDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoeId() {
        return coeId;
    }

    public void setCoeId(Integer coeId) {
        this.coeId = coeId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getOnesId() {
        return onesId;
    }

    public void setOnesId(Integer onesId) {
        this.onesId = onesId;
    }

    public String getOnesLink() {
        return onesLink;
    }

    public void setOnesLink(String onesLink) {
        this.onesLink = onesLink;
    }

    public String getOnesTitle() {
        return onesTitle;
    }

    public void setOnesTitle(String onesTitle) {
        this.onesTitle = onesTitle;
    }

    public Boolean getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Boolean isFinish) {
        this.isFinish = isFinish;
    }

    public Boolean getIsDelay() {
        return isDelay;
    }

    public void setIsDelay(Boolean isDelay) {
        this.isDelay = isDelay;
    }

    public Date getDealline() {
        return dealline;
    }

    public void setDealline(Date dealline) {
        this.dealline = dealline;
    }

    public String getOwnerMis() {
        return ownerMis;
    }

    public void setOwnerMis(String ownerMis) {
        this.ownerMis = ownerMis;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
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
        McdCoeTodoPO other = (McdCoeTodoPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCoeId() == null ? other.getCoeId() == null : this.getCoeId().equals(other.getCoeId()))
            && (this.getOrgName() == null ? other.getOrgName() == null : this.getOrgName().equals(other.getOrgName()))
            && (this.getOnesId() == null ? other.getOnesId() == null : this.getOnesId().equals(other.getOnesId()))
            && (this.getOnesLink() == null ? other.getOnesLink() == null : this.getOnesLink().equals(other.getOnesLink()))
            && (this.getOnesTitle() == null ? other.getOnesTitle() == null : this.getOnesTitle().equals(other.getOnesTitle()))
            && (this.getIsFinish() == null ? other.getIsFinish() == null : this.getIsFinish().equals(other.getIsFinish()))
            && (this.getIsDelay() == null ? other.getIsDelay() == null : this.getIsDelay().equals(other.getIsDelay()))
            && (this.getDealline() == null ? other.getDealline() == null : this.getDealline().equals(other.getDealline()))
            && (this.getOwnerMis() == null ? other.getOwnerMis() == null : this.getOwnerMis().equals(other.getOwnerMis()))
            && (this.getOwnerName() == null ? other.getOwnerName() == null : this.getOwnerName().equals(other.getOwnerName()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getActualDate() == null ? other.getActualDate() == null : this.getActualDate().equals(other.getActualDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCoeId() == null) ? 0 : getCoeId().hashCode());
        result = prime * result + ((getOrgName() == null) ? 0 : getOrgName().hashCode());
        result = prime * result + ((getOnesId() == null) ? 0 : getOnesId().hashCode());
        result = prime * result + ((getOnesLink() == null) ? 0 : getOnesLink().hashCode());
        result = prime * result + ((getOnesTitle() == null) ? 0 : getOnesTitle().hashCode());
        result = prime * result + ((getIsFinish() == null) ? 0 : getIsFinish().hashCode());
        result = prime * result + ((getIsDelay() == null) ? 0 : getIsDelay().hashCode());
        result = prime * result + ((getDealline() == null) ? 0 : getDealline().hashCode());
        result = prime * result + ((getOwnerMis() == null) ? 0 : getOwnerMis().hashCode());
        result = prime * result + ((getOwnerName() == null) ? 0 : getOwnerName().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getActualDate() == null) ? 0 : getActualDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", coeId=").append(coeId);
        sb.append(", orgName=").append(orgName);
        sb.append(", onesId=").append(onesId);
        sb.append(", onesLink=").append(onesLink);
        sb.append(", onesTitle=").append(onesTitle);
        sb.append(", isFinish=").append(isFinish);
        sb.append(", isDelay=").append(isDelay);
        sb.append(", dealline=").append(dealline);
        sb.append(", ownerMis=").append(ownerMis);
        sb.append(", ownerName=").append(ownerName);
        sb.append(", startDate=").append(startDate);
        sb.append(", actualDate=").append(actualDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}