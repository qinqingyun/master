package com.meituan.food.po;

import java.io.Serializable;

public class WeekBugTotalCountPO implements Serializable {
    private Integer id;

    private String groupName;

    private Integer majorCount;

    private Integer blockerCount;

    private Integer criticalCount;

    private Integer minorCount;

    private Integer trivialCount;

    private Integer totalCount;

    private String bugLink;

    private String startDate;

    private String endDate;

    private Long timeFlag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Integer getMajorCount() {
        return majorCount;
    }

    public void setMajorCount(Integer majorCount) {
        this.majorCount = majorCount;
    }

    public Integer getBlockerCount() {
        return blockerCount;
    }

    public void setBlockerCount(Integer blockerCount) {
        this.blockerCount = blockerCount;
    }

    public Integer getCriticalCount() {
        return criticalCount;
    }

    public void setCriticalCount(Integer criticalCount) {
        this.criticalCount = criticalCount;
    }

    public Integer getMinorCount() {
        return minorCount;
    }

    public void setMinorCount(Integer minorCount) {
        this.minorCount = minorCount;
    }

    public Integer getTrivialCount() {
        return trivialCount;
    }

    public void setTrivialCount(Integer trivialCount) {
        this.trivialCount = trivialCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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

    public Long getTimeFlag() {
        return timeFlag;
    }

    public void setTimeFlag(Long timeFlag) {
        this.timeFlag = timeFlag;
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
        WeekBugTotalCountPO other = (WeekBugTotalCountPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGroupName() == null ? other.getGroupName() == null : this.getGroupName().equals(other.getGroupName()))
            && (this.getMajorCount() == null ? other.getMajorCount() == null : this.getMajorCount().equals(other.getMajorCount()))
            && (this.getBlockerCount() == null ? other.getBlockerCount() == null : this.getBlockerCount().equals(other.getBlockerCount()))
            && (this.getCriticalCount() == null ? other.getCriticalCount() == null : this.getCriticalCount().equals(other.getCriticalCount()))
            && (this.getMinorCount() == null ? other.getMinorCount() == null : this.getMinorCount().equals(other.getMinorCount()))
            && (this.getTrivialCount() == null ? other.getTrivialCount() == null : this.getTrivialCount().equals(other.getTrivialCount()))
            && (this.getTotalCount() == null ? other.getTotalCount() == null : this.getTotalCount().equals(other.getTotalCount()))
            && (this.getBugLink() == null ? other.getBugLink() == null : this.getBugLink().equals(other.getBugLink()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getTimeFlag() == null ? other.getTimeFlag() == null : this.getTimeFlag().equals(other.getTimeFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGroupName() == null) ? 0 : getGroupName().hashCode());
        result = prime * result + ((getMajorCount() == null) ? 0 : getMajorCount().hashCode());
        result = prime * result + ((getBlockerCount() == null) ? 0 : getBlockerCount().hashCode());
        result = prime * result + ((getCriticalCount() == null) ? 0 : getCriticalCount().hashCode());
        result = prime * result + ((getMinorCount() == null) ? 0 : getMinorCount().hashCode());
        result = prime * result + ((getTrivialCount() == null) ? 0 : getTrivialCount().hashCode());
        result = prime * result + ((getTotalCount() == null) ? 0 : getTotalCount().hashCode());
        result = prime * result + ((getBugLink() == null) ? 0 : getBugLink().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getTimeFlag() == null) ? 0 : getTimeFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupName=").append(groupName);
        sb.append(", majorCount=").append(majorCount);
        sb.append(", blockerCount=").append(blockerCount);
        sb.append(", criticalCount=").append(criticalCount);
        sb.append(", minorCount=").append(minorCount);
        sb.append(", trivialCount=").append(trivialCount);
        sb.append(", totalCount=").append(totalCount);
        sb.append(", bugLink=").append(bugLink);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", timeFlag=").append(timeFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}