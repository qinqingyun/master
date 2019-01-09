package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WeekMomaCrashPO implements Serializable {
    private Integer id;

    private Integer momaCrashCount;

    private BigDecimal momaCrashRate;

    private Integer beeCrashCount;

    private BigDecimal beeCrashRate;

    private Integer aboluoCrashCount;

    private BigDecimal aboluoCrashRate;

    private String startDate;

    private String endDate;

    private String platform;

    private String os;

    private Date createdAt;

    private String dateRange;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMomaCrashCount() {
        return momaCrashCount;
    }

    public void setMomaCrashCount(Integer momaCrashCount) {
        this.momaCrashCount = momaCrashCount;
    }

    public BigDecimal getMomaCrashRate() {
        return momaCrashRate;
    }

    public void setMomaCrashRate(BigDecimal momaCrashRate) {
        this.momaCrashRate = momaCrashRate;
    }

    public Integer getBeeCrashCount() {
        return beeCrashCount;
    }

    public void setBeeCrashCount(Integer beeCrashCount) {
        this.beeCrashCount = beeCrashCount;
    }

    public BigDecimal getBeeCrashRate() {
        return beeCrashRate;
    }

    public void setBeeCrashRate(BigDecimal beeCrashRate) {
        this.beeCrashRate = beeCrashRate;
    }

    public Integer getAboluoCrashCount() {
        return aboluoCrashCount;
    }

    public void setAboluoCrashCount(Integer aboluoCrashCount) {
        this.aboluoCrashCount = aboluoCrashCount;
    }

    public BigDecimal getAboluoCrashRate() {
        return aboluoCrashRate;
    }

    public void setAboluoCrashRate(BigDecimal aboluoCrashRate) {
        this.aboluoCrashRate = aboluoCrashRate;
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
        WeekMomaCrashPO other = (WeekMomaCrashPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMomaCrashCount() == null ? other.getMomaCrashCount() == null : this.getMomaCrashCount().equals(other.getMomaCrashCount()))
            && (this.getMomaCrashRate() == null ? other.getMomaCrashRate() == null : this.getMomaCrashRate().equals(other.getMomaCrashRate()))
            && (this.getBeeCrashCount() == null ? other.getBeeCrashCount() == null : this.getBeeCrashCount().equals(other.getBeeCrashCount()))
            && (this.getBeeCrashRate() == null ? other.getBeeCrashRate() == null : this.getBeeCrashRate().equals(other.getBeeCrashRate()))
            && (this.getAboluoCrashCount() == null ? other.getAboluoCrashCount() == null : this.getAboluoCrashCount().equals(other.getAboluoCrashCount()))
            && (this.getAboluoCrashRate() == null ? other.getAboluoCrashRate() == null : this.getAboluoCrashRate().equals(other.getAboluoCrashRate()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getOs() == null ? other.getOs() == null : this.getOs().equals(other.getOs()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getDateRange() == null ? other.getDateRange() == null : this.getDateRange().equals(other.getDateRange()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMomaCrashCount() == null) ? 0 : getMomaCrashCount().hashCode());
        result = prime * result + ((getMomaCrashRate() == null) ? 0 : getMomaCrashRate().hashCode());
        result = prime * result + ((getBeeCrashCount() == null) ? 0 : getBeeCrashCount().hashCode());
        result = prime * result + ((getBeeCrashRate() == null) ? 0 : getBeeCrashRate().hashCode());
        result = prime * result + ((getAboluoCrashCount() == null) ? 0 : getAboluoCrashCount().hashCode());
        result = prime * result + ((getAboluoCrashRate() == null) ? 0 : getAboluoCrashRate().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getDateRange() == null) ? 0 : getDateRange().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", momaCrashCount=").append(momaCrashCount);
        sb.append(", momaCrashRate=").append(momaCrashRate);
        sb.append(", beeCrashCount=").append(beeCrashCount);
        sb.append(", beeCrashRate=").append(beeCrashRate);
        sb.append(", aboluoCrashCount=").append(aboluoCrashCount);
        sb.append(", aboluoCrashRate=").append(aboluoCrashRate);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", platform=").append(platform);
        sb.append(", os=").append(os);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", dateRange=").append(dateRange);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}