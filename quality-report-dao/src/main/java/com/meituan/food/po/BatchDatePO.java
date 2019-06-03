package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class BatchDatePO implements Serializable {
    private Integer id;

    private Date coverageDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCoverageDate() {
        return coverageDate;
    }

    public void setCoverageDate(Date coverageDate) {
        this.coverageDate = coverageDate;
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
        BatchDatePO other = (BatchDatePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCoverageDate() == null ? other.getCoverageDate() == null : this.getCoverageDate().equals(other.getCoverageDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCoverageDate() == null) ? 0 : getCoverageDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", coverageDate=").append(coverageDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}