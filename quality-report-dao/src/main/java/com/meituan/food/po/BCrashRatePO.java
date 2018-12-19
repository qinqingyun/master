package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BCrashRatePO implements Serializable {
    private Integer id;

    private BigDecimal bCrashRate;

    private String month;

    private BigDecimal bDianping;

    private BigDecimal bWaimai;

    private Date createdAt;

    private Date updatedAt;

    private String platfrom;

    private String os;

    private Integer bCrashCount;

    private Integer bDianpingCrashCount;

    private Integer bWaimaiCrashCount;

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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
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

    public String getPlatfrom() {
        return platfrom;
    }

    public void setPlatfrom(String platfrom) {
        this.platfrom = platfrom == null ? null : platfrom.trim();
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
        BCrashRatePO other = (BCrashRatePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getbCrashRate() == null ? other.getbCrashRate() == null : this.getbCrashRate().equals(other.getbCrashRate()))
            && (this.getMonth() == null ? other.getMonth() == null : this.getMonth().equals(other.getMonth()))
            && (this.getbDianping() == null ? other.getbDianping() == null : this.getbDianping().equals(other.getbDianping()))
            && (this.getbWaimai() == null ? other.getbWaimai() == null : this.getbWaimai().equals(other.getbWaimai()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getPlatfrom() == null ? other.getPlatfrom() == null : this.getPlatfrom().equals(other.getPlatfrom()))
            && (this.getOs() == null ? other.getOs() == null : this.getOs().equals(other.getOs()))
            && (this.getbCrashCount() == null ? other.getbCrashCount() == null : this.getbCrashCount().equals(other.getbCrashCount()))
            && (this.getbDianpingCrashCount() == null ? other.getbDianpingCrashCount() == null : this.getbDianpingCrashCount().equals(other.getbDianpingCrashCount()))
            && (this.getbWaimaiCrashCount() == null ? other.getbWaimaiCrashCount() == null : this.getbWaimaiCrashCount().equals(other.getbWaimaiCrashCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getbCrashRate() == null) ? 0 : getbCrashRate().hashCode());
        result = prime * result + ((getMonth() == null) ? 0 : getMonth().hashCode());
        result = prime * result + ((getbDianping() == null) ? 0 : getbDianping().hashCode());
        result = prime * result + ((getbWaimai() == null) ? 0 : getbWaimai().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getPlatfrom() == null) ? 0 : getPlatfrom().hashCode());
        result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
        result = prime * result + ((getbCrashCount() == null) ? 0 : getbCrashCount().hashCode());
        result = prime * result + ((getbDianpingCrashCount() == null) ? 0 : getbDianpingCrashCount().hashCode());
        result = prime * result + ((getbWaimaiCrashCount() == null) ? 0 : getbWaimaiCrashCount().hashCode());
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
        sb.append(", month=").append(month);
        sb.append(", bDianping=").append(bDianping);
        sb.append(", bWaimai=").append(bWaimai);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", platfrom=").append(platfrom);
        sb.append(", os=").append(os);
        sb.append(", bCrashCount=").append(bCrashCount);
        sb.append(", bDianpingCrashCount=").append(bDianpingCrashCount);
        sb.append(", bWaimaiCrashCount=").append(bWaimaiCrashCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}