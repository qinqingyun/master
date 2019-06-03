package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class CargoDataPO implements Serializable {
    private Integer id;

    private String stackuuid;

    private Integer stableSuccess;

    private Integer stableTotal;

    private Integer avalibleSuccess;

    private Integer avalibleTotal;

    private String tag;

    private String person;

    private String direction;

    private String stableTagPercentage;

    private String avalibleTagPercentage;

    private Date date;

    private String comment;

    private Date updatedDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStackuuid() {
        return stackuuid;
    }

    public void setStackuuid(String stackuuid) {
        this.stackuuid = stackuuid == null ? null : stackuuid.trim();
    }

    public Integer getStableSuccess() {
        return stableSuccess;
    }

    public void setStableSuccess(Integer stableSuccess) {
        this.stableSuccess = stableSuccess;
    }

    public Integer getStableTotal() {
        return stableTotal;
    }

    public void setStableTotal(Integer stableTotal) {
        this.stableTotal = stableTotal;
    }

    public Integer getAvalibleSuccess() {
        return avalibleSuccess;
    }

    public void setAvalibleSuccess(Integer avalibleSuccess) {
        this.avalibleSuccess = avalibleSuccess;
    }

    public Integer getAvalibleTotal() {
        return avalibleTotal;
    }

    public void setAvalibleTotal(Integer avalibleTotal) {
        this.avalibleTotal = avalibleTotal;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public String getStableTagPercentage() {
        return stableTagPercentage;
    }

    public void setStableTagPercentage(String stableTagPercentage) {
        this.stableTagPercentage = stableTagPercentage == null ? null : stableTagPercentage.trim();
    }

    public String getAvalibleTagPercentage() {
        return avalibleTagPercentage;
    }

    public void setAvalibleTagPercentage(String avalibleTagPercentage) {
        this.avalibleTagPercentage = avalibleTagPercentage == null ? null : avalibleTagPercentage.trim();
    }

    public void init(){
        this.setDate(new Date());
        this.setComment("测试");
        this.setDirection("");
        this.setUpdatedDate(new Date());
        this.setStableTotal(0);
        this.setStableSuccess(0);
        this.setStableTagPercentage("");
        this.setAvalibleTagPercentage("");
        this.setAvalibleTotal(0);
        this.setAvalibleSuccess(0);
        this.setStackuuid("");
        this.setTag("");
        this.setId(0);
        this.setPerson("");
        this.setDirection("C");
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
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
        CargoDataPO other = (CargoDataPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStackuuid() == null ? other.getStackuuid() == null : this.getStackuuid().equals(other.getStackuuid()))
            && (this.getStableSuccess() == null ? other.getStableSuccess() == null : this.getStableSuccess().equals(other.getStableSuccess()))
            && (this.getStableTotal() == null ? other.getStableTotal() == null : this.getStableTotal().equals(other.getStableTotal()))
            && (this.getAvalibleSuccess() == null ? other.getAvalibleSuccess() == null : this.getAvalibleSuccess().equals(other.getAvalibleSuccess()))
            && (this.getAvalibleTotal() == null ? other.getAvalibleTotal() == null : this.getAvalibleTotal().equals(other.getAvalibleTotal()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getPerson() == null ? other.getPerson() == null : this.getPerson().equals(other.getPerson()))
            && (this.getDirection() == null ? other.getDirection() == null : this.getDirection().equals(other.getDirection()))
            && (this.getStableTagPercentage() == null ? other.getStableTagPercentage() == null : this.getStableTagPercentage().equals(other.getStableTagPercentage()))
            && (this.getAvalibleTagPercentage() == null ? other.getAvalibleTagPercentage() == null : this.getAvalibleTagPercentage().equals(other.getAvalibleTagPercentage()))
            && (this.getDate() == null ? other.getDate() == null : this.getDate().equals(other.getDate()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getUpdatedDate() == null ? other.getUpdatedDate() == null : this.getUpdatedDate().equals(other.getUpdatedDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStackuuid() == null) ? 0 : getStackuuid().hashCode());
        result = prime * result + ((getStableSuccess() == null) ? 0 : getStableSuccess().hashCode());
        result = prime * result + ((getStableTotal() == null) ? 0 : getStableTotal().hashCode());
        result = prime * result + ((getAvalibleSuccess() == null) ? 0 : getAvalibleSuccess().hashCode());
        result = prime * result + ((getAvalibleTotal() == null) ? 0 : getAvalibleTotal().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getPerson() == null) ? 0 : getPerson().hashCode());
        result = prime * result + ((getDirection() == null) ? 0 : getDirection().hashCode());
        result = prime * result + ((getStableTagPercentage() == null) ? 0 : getStableTagPercentage().hashCode());
        result = prime * result + ((getAvalibleTagPercentage() == null) ? 0 : getAvalibleTagPercentage().hashCode());
        result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getUpdatedDate() == null) ? 0 : getUpdatedDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stackuuid=").append(stackuuid);
        sb.append(", stableSuccess=").append(stableSuccess);
        sb.append(", stableTotal=").append(stableTotal);
        sb.append(", avalibleSuccess=").append(avalibleSuccess);
        sb.append(", avalibleTotal=").append(avalibleTotal);
        sb.append(", tag=").append(tag);
        sb.append(", person=").append(person);
        sb.append(", direction=").append(direction);
        sb.append(", stableTagPercentage=").append(stableTagPercentage);
        sb.append(", avalibleTagPercentage=").append(avalibleTagPercentage);
        sb.append(", date=").append(date);
        sb.append(", comment=").append(comment);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}