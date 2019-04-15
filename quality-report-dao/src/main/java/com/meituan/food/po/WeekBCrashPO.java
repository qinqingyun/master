package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WeekBCrashPO implements Serializable {
    private Integer id;

    private BigDecimal bCrashRate;

    private BigDecimal bDianping;

    private BigDecimal bWaimai;

    private String platform;

    private String os;

    private Integer bCrashCount;

    private Integer bDianpingCrashCount;

    private Integer bWaimaiCrashCount;

    private String startDate;

    private String endDate;

    private Date createdAt;

    private String dateRange;

    private Integer flag;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getbCrashRate() {
        return bCrashRate;
    }

    public void setbCrashRate(BigDecimal bCrashRate) {
        this.bCrashRate = bCrashRate;
    }

    public BigDecimal getbDianping() {
        return bDianping;
    }

    public void setbDianping(BigDecimal bDianping) {
        this.bDianping = bDianping;
    }

    public BigDecimal getbWaimai() {
        return bWaimai;
    }

    public void setbWaimai(BigDecimal bWaimai) {
        this.bWaimai = bWaimai;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
    }

    public Integer getbCrashCount() {
        return bCrashCount;
    }

    public void setbCrashCount(Integer bCrashCount) {
        this.bCrashCount = bCrashCount;
    }

    public Integer getbDianpingCrashCount() {
        return bDianpingCrashCount;
    }

    public void setbDianpingCrashCount(Integer bDianpingCrashCount) {
        this.bDianpingCrashCount = bDianpingCrashCount;
    }

    public Integer getbWaimaiCrashCount() {
        return bWaimaiCrashCount;
    }

    public void setbWaimaiCrashCount(Integer bWaimaiCrashCount) {
        this.bWaimaiCrashCount = bWaimaiCrashCount;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange == null ? null : dateRange.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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
        WeekBCrashPO other = (WeekBCrashPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getbCrashRate() == null ? other.getbCrashRate() == null : this.getbCrashRate().equals(other.getbCrashRate()))
            && (this.getbDianping() == null ? other.getbDianping() == null : this.getbDianping().equals(other.getbDianping()))
            && (this.getbWaimai() == null ? other.getbWaimai() == null : this.getbWaimai().equals(other.getbWaimai()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getOs() == null ? other.getOs() == null : this.getOs().equals(other.getOs()))
            && (this.getbCrashCount() == null ? other.getbCrashCount() == null : this.getbCrashCount().equals(other.getbCrashCount()))
            && (this.getbDianpingCrashCount() == null ? other.getbDianpingCrashCount() == null : this.getbDianpingCrashCount().equals(other.getbDianpingCrashCount()))
            && (this.getbWaimaiCrashCount() == null ? other.getbWaimaiCrashCount() == null : this.getbWaimaiCrashCount().equals(other.getbWaimaiCrashCount()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getDateRange() == null ? other.getDateRange() == null : this.getDateRange().equals(other.getDateRange()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getbCrashRate() == null) ? 0 : getbCrashRate().hashCode());
        result = prime * result + ((getbDianping() == null) ? 0 : getbDianping().hashCode());
        result = prime * result + ((getbWaimai() == null) ? 0 : getbWaimai().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
        result = prime * result + ((getbCrashCount() == null) ? 0 : getbCrashCount().hashCode());
        result = prime * result + ((getbDianpingCrashCount() == null) ? 0 : getbDianpingCrashCount().hashCode());
        result = prime * result + ((getbWaimaiCrashCount() == null) ? 0 : getbWaimaiCrashCount().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDateRange() == null) ? 0 : getDateRange().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bCrashRate=").append(bCrashRate);
        sb.append(", bDianping=").append(bDianping);
        sb.append(", bWaimai=").append(bWaimai);
        sb.append(", platform=").append(platform);
        sb.append(", os=").append(os);
        sb.append(", bCrashCount=").append(bCrashCount);
        sb.append(", bDianpingCrashCount=").append(bDianpingCrashCount);
        sb.append(", bWaimaiCrashCount=").append(bWaimaiCrashCount);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", dateRange=").append(dateRange);
        sb.append(", flag=").append(flag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}