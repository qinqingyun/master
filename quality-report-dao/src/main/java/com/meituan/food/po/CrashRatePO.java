package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CrashRatePO implements Serializable {
    private Integer id;

    private Integer crash;

    private String plantform;

    private Integer dau;

    private String crashRate;

    private String startDate;

    private String endDate;

    private String showDateRange;

    private Date createdAt;

    private Date updatedAt;

    private BigDecimal finalRate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrash() {
        return crash;
    }

    public void setCrash(Integer crash) {
        this.crash = crash;
    }

    public String getPlantform() {
        return plantform;
    }

    public void setPlantform(String plantform) {
        this.plantform = plantform == null ? null : plantform.trim();
    }

    public Integer getDau() {
        return dau;
    }

    public void setDau(Integer dau) {
        this.dau = dau;
    }

    public String getCrashRate() {
        return crashRate;
    }

    public void setCrashRate(String crashRate) {
        this.crashRate = crashRate == null ? null : crashRate.trim();
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

    public String getShowDateRange() {
        return showDateRange;
    }

    public void setShowDateRange(String showDateRange) {
        this.showDateRange = showDateRange == null ? null : showDateRange.trim();
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

    public BigDecimal getFinalRate() {
        return finalRate;
    }

    public void setFinalRate(BigDecimal finalRate) {
        this.finalRate = finalRate;
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
        CrashRatePO other = (CrashRatePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCrash() == null ? other.getCrash() == null : this.getCrash().equals(other.getCrash()))
            && (this.getPlantform() == null ? other.getPlantform() == null : this.getPlantform().equals(other.getPlantform()))
            && (this.getDau() == null ? other.getDau() == null : this.getDau().equals(other.getDau()))
            && (this.getCrashRate() == null ? other.getCrashRate() == null : this.getCrashRate().equals(other.getCrashRate()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getShowDateRange() == null ? other.getShowDateRange() == null : this.getShowDateRange().equals(other.getShowDateRange()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getFinalRate() == null ? other.getFinalRate() == null : this.getFinalRate().equals(other.getFinalRate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCrash() == null) ? 0 : getCrash().hashCode());
        result = prime * result + ((getPlantform() == null) ? 0 : getPlantform().hashCode());
        result = prime * result + ((getDau() == null) ? 0 : getDau().hashCode());
        result = prime * result + ((getCrashRate() == null) ? 0 : getCrashRate().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getShowDateRange() == null) ? 0 : getShowDateRange().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getFinalRate() == null) ? 0 : getFinalRate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", crash=").append(crash);
        sb.append(", plantform=").append(plantform);
        sb.append(", dau=").append(dau);
        sb.append(", crashRate=").append(crashRate);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", showDateRange=").append(showDateRange);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", finalRate=").append(finalRate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}