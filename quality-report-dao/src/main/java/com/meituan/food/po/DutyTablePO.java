package com.meituan.food.po;

import java.io.Serializable;

public class DutyTablePO implements Serializable {
    private Integer id;

    private String dutyName;

    private String dutyMis;

    private Boolean isOnDuty;

    private String dutyDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName == null ? null : dutyName.trim();
    }

    public String getDutyMis() {
        return dutyMis;
    }

    public void setDutyMis(String dutyMis) {
        this.dutyMis = dutyMis == null ? null : dutyMis.trim();
    }

    public Boolean getIsOnDuty() {
        return isOnDuty;
    }

    public void setIsOnDuty(Boolean isOnDuty) {
        this.isOnDuty = isOnDuty;
    }

    public String getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(String dutyDate) {
        this.dutyDate = dutyDate == null ? null : dutyDate.trim();
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
        DutyTablePO other = (DutyTablePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDutyName() == null ? other.getDutyName() == null : this.getDutyName().equals(other.getDutyName()))
            && (this.getDutyMis() == null ? other.getDutyMis() == null : this.getDutyMis().equals(other.getDutyMis()))
            && (this.getIsOnDuty() == null ? other.getIsOnDuty() == null : this.getIsOnDuty().equals(other.getIsOnDuty()))
            && (this.getDutyDate() == null ? other.getDutyDate() == null : this.getDutyDate().equals(other.getDutyDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDutyName() == null) ? 0 : getDutyName().hashCode());
        result = prime * result + ((getDutyMis() == null) ? 0 : getDutyMis().hashCode());
        result = prime * result + ((getIsOnDuty() == null) ? 0 : getIsOnDuty().hashCode());
        result = prime * result + ((getDutyDate() == null) ? 0 : getDutyDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dutyName=").append(dutyName);
        sb.append(", dutyMis=").append(dutyMis);
        sb.append(", isOnDuty=").append(isOnDuty);
        sb.append(", dutyDate=").append(dutyDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}