package com.meituan.food.po;

import java.io.Serializable;

public class CoeAtpPO implements Serializable {
    private Integer id;

    private Integer coeId;

    private Boolean isPush;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoeId() {
        return coeId;
    }

    public void setCoeId(Integer coeId) {
        this.coeId = coeId;
    }

    public Boolean getIsPush() {
        return isPush;
    }

    public void setIsPush(Boolean isPush) {
        this.isPush = isPush;
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
        CoeAtpPO other = (CoeAtpPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCoeId() == null ? other.getCoeId() == null : this.getCoeId().equals(other.getCoeId()))
            && (this.getIsPush() == null ? other.getIsPush() == null : this.getIsPush().equals(other.getIsPush()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCoeId() == null) ? 0 : getCoeId().hashCode());
        result = prime * result + ((getIsPush() == null) ? 0 : getIsPush().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", coeId=").append(coeId);
        sb.append(", isPush=").append(isPush);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}