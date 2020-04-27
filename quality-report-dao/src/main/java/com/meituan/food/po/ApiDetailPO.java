package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApiDetailPO implements Serializable {
    private Integer id;

    private String appkey;

    private String apiFullName;

    private Long callCount;

    private String apiSpanName;

    private BigDecimal proportion;

    private Integer isCore;

    private Date createdTime;

    private Date updatedAt;


    private Integer isCovered;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public String getApiFullName() {
        return apiFullName;
    }

    public void setApiFullName(String apiFullName) {
        this.apiFullName = apiFullName == null ? null : apiFullName.trim();
    }

    public Long getCallCount() {
        return callCount;
    }

    public void setCallCount(Long callCount) {
        this.callCount = callCount;
    }

    public String getApiSpanName() {
        return apiSpanName;
    }

    public void setApiSpanName(String apiSpanName) {
        this.apiSpanName = apiSpanName;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public Integer getIsCore() {
        return isCore;
    }

    public void setIsCore(Integer isCore) {
        this.isCore = isCore;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIsCovered() {
        return isCovered;
    }

    public void setIsCovered(Integer isCovered) {
        this.isCovered = isCovered;
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
        ApiDetailPO other = (ApiDetailPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAppkey() == null ? other.getAppkey() == null : this.getAppkey().equals(other.getAppkey()))
            && (this.getApiFullName() == null ? other.getApiFullName() == null : this.getApiFullName().equals(other.getApiFullName()))
            && (this.getCallCount() == null ? other.getCallCount() == null : this.getCallCount().equals(other.getCallCount()))
            && (this.getApiSpanName() == null ? other.getApiSpanName() == null : this.getApiSpanName().equals(other.getApiSpanName()))
            && (this.getProportion() == null ? other.getProportion() == null : this.getProportion().equals(other.getProportion()))
            && (this.getIsCore() == null ? other.getIsCore() == null : this.getIsCore().equals(other.getIsCore()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppkey() == null) ? 0 : getAppkey().hashCode());
        result = prime * result + ((getApiFullName() == null) ? 0 : getApiFullName().hashCode());
        result = prime * result + ((getCallCount() == null) ? 0 : getCallCount().hashCode());
        result = prime * result + ((getApiSpanName() == null) ? 0 : getApiSpanName().hashCode());
        result = prime * result + ((getProportion() == null) ? 0 : getProportion().hashCode());
        result = prime * result + ((getIsCore() == null) ? 0 : getIsCore().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
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
        sb.append(", appkey=").append(appkey);
        sb.append(", apiFullName=").append(apiFullName);
        sb.append(", callCount=").append(callCount);
        sb.append(", apiSpanName=").append(apiSpanName);
        sb.append(", proportion=").append(proportion);
        sb.append(", isCore=").append(isCore);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}