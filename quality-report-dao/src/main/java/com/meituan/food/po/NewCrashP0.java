package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class NewCrashP0 implements Serializable {
    private Integer id;

    private String platform;

    private Integer crash;

    private BigDecimal crashRate;

    private Date crashDate;

    private Date createdTime;

    private Integer flag;

    private String dateRange;

    private String os;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public Integer getCrash() {
        return crash;
    }

    public void setCrash(Integer crash) {
        this.crash = crash;
    }

    public BigDecimal getCrashRate() {
        return crashRate;
    }

    public void setCrashRate(BigDecimal crashRate) {
        this.crashRate = crashRate;
    }

    public Date getCrashDate() {
        return crashDate;
    }

    public void setCrashDate(Date crashDate) {
        this.crashDate = crashDate;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange == null ? null : dateRange.trim();
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
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
        NewCrashP0 other = (NewCrashP0) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getCrash() == null ? other.getCrash() == null : this.getCrash().equals(other.getCrash()))
            && (this.getCrashRate() == null ? other.getCrashRate() == null : this.getCrashRate().equals(other.getCrashRate()))
            && (this.getCrashDate() == null ? other.getCrashDate() == null : this.getCrashDate().equals(other.getCrashDate()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getDateRange() == null ? other.getDateRange() == null : this.getDateRange().equals(other.getDateRange()))
            && (this.getOs() == null ? other.getOs() == null : this.getOs().equals(other.getOs()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getCrash() == null) ? 0 : getCrash().hashCode());
        result = prime * result + ((getCrashRate() == null) ? 0 : getCrashRate().hashCode());
        result = prime * result + ((getCrashDate() == null) ? 0 : getCrashDate().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getDateRange() == null) ? 0 : getDateRange().hashCode());
        result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", platform=").append(platform);
        sb.append(", crash=").append(crash);
        sb.append(", crashRate=").append(crashRate);
        sb.append(", crashDate=").append(crashDate);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", flag=").append(flag);
        sb.append(", dateRange=").append(dateRange);
        sb.append(", os=").append(os);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}