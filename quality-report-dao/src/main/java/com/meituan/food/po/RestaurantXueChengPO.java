package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class RestaurantXueChengPO implements Serializable {
    private Integer id;

    private Long createCount;

    private Long updateCount;

    private String partitionDate;

    private String misId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCreateCount() {
        return createCount;
    }

    public void setCreateCount(Long createCount) {
        this.createCount = createCount;
    }

    public Long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(Long updateCount) {
        this.updateCount = updateCount;
    }

    public String getPartitionDate() {
        return partitionDate;
    }

    public void setPartitionDate(String partitionDate) {
        this.partitionDate = partitionDate == null ? null : partitionDate.trim();
    }

    public String getMisId() {
        return misId;
    }

    public void setMisId(String misId) {
        this.misId = misId == null ? null : misId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        RestaurantXueChengPO other = (RestaurantXueChengPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateCount() == null ? other.getCreateCount() == null : this.getCreateCount().equals(other.getCreateCount()))
            && (this.getUpdateCount() == null ? other.getUpdateCount() == null : this.getUpdateCount().equals(other.getUpdateCount()))
            && (this.getPartitionDate() == null ? other.getPartitionDate() == null : this.getPartitionDate().equals(other.getPartitionDate()))
            && (this.getMisId() == null ? other.getMisId() == null : this.getMisId().equals(other.getMisId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateCount() == null) ? 0 : getCreateCount().hashCode());
        result = prime * result + ((getUpdateCount() == null) ? 0 : getUpdateCount().hashCode());
        result = prime * result + ((getPartitionDate() == null) ? 0 : getPartitionDate().hashCode());
        result = prime * result + ((getMisId() == null) ? 0 : getMisId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createCount=").append(createCount);
        sb.append(", updateCount=").append(updateCount);
        sb.append(", partitionDate=").append(partitionDate);
        sb.append(", misId=").append(misId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}