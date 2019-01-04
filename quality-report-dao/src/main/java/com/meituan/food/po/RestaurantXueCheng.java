package com.meituan.food.po;

import java.util.Date;

public class RestaurantXueCheng {

    private int  id;

    private long createCount;

    private long updateCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateCount() {
        return createCount;
    }

    public void setCreateCount(long createCount) {
        this.createCount = createCount;
    }

    public long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(long updateCount) {
        this.updateCount = updateCount;
    }

    public String getPartitionDate() {
        return partitionDate;
    }

    public void setPartitionDate(String partitionDate) {
        this.partitionDate = partitionDate;
    }

    public String getMisId() {
        return misId;
    }

    public void setMisId(String misId) {
        this.misId = misId;
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

    @Override
    public String toString() {
        return "RestaurantXueCheng{" +
                "id=" + id +
                ", createCount=" + createCount +
                ", updateCount=" + updateCount +
                ", partitionDate='" + partitionDate + '\'' +
                ", misId='" + misId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private String partitionDate;

    private String misId;

    private Date createTime;

    private Date updateTime;


}
