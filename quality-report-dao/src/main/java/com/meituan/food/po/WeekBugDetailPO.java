package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class WeekBugDetailPO implements Serializable {
    private Integer id;

    private String title;

    private String bugLevel;

    private String reason;

    private String creator;

    private String receiver;

    private String bugStatus;

    private String createdTime;

    private Long timeFlag;

    private String orgname;

    private String orgid;

    private String allTitle;

    private String bugLink;

    private String startDate;

    private String endDate;

    private Date createdTimeDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBugLevel() {
        return bugLevel;
    }

    public void setBugLevel(String bugLevel) {
        this.bugLevel = bugLevel == null ? null : bugLevel.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus == null ? null : bugStatus.trim();
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime == null ? null : createdTime.trim();
    }

    public Long getTimeFlag() {
        return timeFlag;
    }

    public void setTimeFlag(Long timeFlag) {
        this.timeFlag = timeFlag;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getAllTitle() {
        return allTitle;
    }

    public void setAllTitle(String allTitle) {
        this.allTitle = allTitle == null ? null : allTitle.trim();
    }

    public String getBugLink() {
        return bugLink;
    }

    public void setBugLink(String bugLink) {
        this.bugLink = bugLink == null ? null : bugLink.trim();
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

    public Date getCreatedTimeDate() {
        return createdTimeDate;
    }

    public void setCreatedTimeDate(Date createdTimeDate) {
        this.createdTimeDate = createdTimeDate;
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
        WeekBugDetailPO other = (WeekBugDetailPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getBugLevel() == null ? other.getBugLevel() == null : this.getBugLevel().equals(other.getBugLevel()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getReceiver() == null ? other.getReceiver() == null : this.getReceiver().equals(other.getReceiver()))
            && (this.getBugStatus() == null ? other.getBugStatus() == null : this.getBugStatus().equals(other.getBugStatus()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getTimeFlag() == null ? other.getTimeFlag() == null : this.getTimeFlag().equals(other.getTimeFlag()))
            && (this.getOrgname() == null ? other.getOrgname() == null : this.getOrgname().equals(other.getOrgname()))
            && (this.getOrgid() == null ? other.getOrgid() == null : this.getOrgid().equals(other.getOrgid()))
            && (this.getAllTitle() == null ? other.getAllTitle() == null : this.getAllTitle().equals(other.getAllTitle()))
            && (this.getBugLink() == null ? other.getBugLink() == null : this.getBugLink().equals(other.getBugLink()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getCreatedTimeDate() == null ? other.getCreatedTimeDate() == null : this.getCreatedTimeDate().equals(other.getCreatedTimeDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getBugLevel() == null) ? 0 : getBugLevel().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getReceiver() == null) ? 0 : getReceiver().hashCode());
        result = prime * result + ((getBugStatus() == null) ? 0 : getBugStatus().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getTimeFlag() == null) ? 0 : getTimeFlag().hashCode());
        result = prime * result + ((getOrgname() == null) ? 0 : getOrgname().hashCode());
        result = prime * result + ((getOrgid() == null) ? 0 : getOrgid().hashCode());
        result = prime * result + ((getAllTitle() == null) ? 0 : getAllTitle().hashCode());
        result = prime * result + ((getBugLink() == null) ? 0 : getBugLink().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getCreatedTimeDate() == null) ? 0 : getCreatedTimeDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", bugLevel=").append(bugLevel);
        sb.append(", reason=").append(reason);
        sb.append(", creator=").append(creator);
        sb.append(", receiver=").append(receiver);
        sb.append(", bugStatus=").append(bugStatus);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", timeFlag=").append(timeFlag);
        sb.append(", orgname=").append(orgname);
        sb.append(", orgid=").append(orgid);
        sb.append(", allTitle=").append(allTitle);
        sb.append(", bugLink=").append(bugLink);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", createdTimeDate=").append(createdTimeDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}