package com.meituan.food.po;

import java.io.Serializable;

public class McdInfoPO implements Serializable {
    private Integer id;

    private Integer mcdId;

    private String mcdName;

    private Integer mcdFatherId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMcdId() {
        return mcdId;
    }

    public void setMcdId(Integer mcdId) {
        this.mcdId = mcdId;
    }

    public String getMcdName() {
        return mcdName;
    }

    public void setMcdName(String mcdName) {
        this.mcdName = mcdName;
    }

    public Integer getMcdFatherId() {
        return mcdFatherId;
    }

    public void setMcdFatherId(Integer mcdFatherId) {
        this.mcdFatherId = mcdFatherId;
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
        McdInfoPO other = (McdInfoPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMcdId() == null ? other.getMcdId() == null : this.getMcdId().equals(other.getMcdId()))
            && (this.getMcdName() == null ? other.getMcdName() == null : this.getMcdName().equals(other.getMcdName()))
            && (this.getMcdFatherId() == null ? other.getMcdFatherId() == null : this.getMcdFatherId().equals(other.getMcdFatherId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMcdId() == null) ? 0 : getMcdId().hashCode());
        result = prime * result + ((getMcdName() == null) ? 0 : getMcdName().hashCode());
        result = prime * result + ((getMcdFatherId() == null) ? 0 : getMcdFatherId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mcdId=").append(mcdId);
        sb.append(", mcdName=").append(mcdName);
        sb.append(", mcdFatherId=").append(mcdFatherId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}