package com.meituan.food.po;

import java.util.Date;

public class RestaurantDau {

    private int  id;

    private long cateringDau;

    private String partitionDate;

    private String os;

    private String partitionApp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCateringDau() {
        return cateringDau;
    }

    public void setCateringDau(long cateringDau) {
        this.cateringDau = cateringDau;
    }

    public String getPartitionDate() {
        return partitionDate;
    }

    public void setPartitionDate(String partitionDate) {
        this.partitionDate = partitionDate;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPartitionApp() {
        return partitionApp;
    }

    public void setPartitionApp(String partitionApp) {
        this.partitionApp = partitionApp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    private String version;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "RestaurantDau{" +
                "id=" + id +
                ", cateringDau=" + cateringDau +
                ", partitionDate='" + partitionDate + '\'' +
                ", os='" + os + '\'' +
                ", partitionApp='" + partitionApp + '\'' +
                ", version='" + version + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
