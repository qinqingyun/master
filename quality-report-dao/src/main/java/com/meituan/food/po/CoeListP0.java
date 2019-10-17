package com.meituan.food.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CoeListP0 implements Serializable {
    private Integer id;

    private Integer coeId;

    private String brief;

    private Date occurDate;

    private Date notifyTime;

    private Date findTime;

    private Date locationTime;

    private Date handleTime;

    private Date solvedTime;

    private Integer fminusoTime;

    private Integer lminusfTime;

    private Integer sminushTime;

    private String wiki;

    private String level;

    private String ownerName;

    private String ownerMis;

    private String qaName;

    private String qaMis;

    private String coeLink;

    private String category;

    private BigDecimal rdShare;

    private BigDecimal qaShare;

    private Boolean joinStatus;

    private String appearance;

    private String subCategory;

    private Integer allTodo;

    private Integer notFinishTodo;

    private Integer finishTodo;

    private String notFinishTodoTask;

    private Boolean available;

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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Date getFindTime() {
        return findTime;
    }

    public void setFindTime(Date findTime) {
        this.findTime = findTime;
    }

    public Date getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(Date locationTime) {
        this.locationTime = locationTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getSolvedTime() {
        return solvedTime;
    }

    public void setSolvedTime(Date solvedTime) {
        this.solvedTime = solvedTime;
    }

    public Integer getFminusoTime() {
        return fminusoTime;
    }

    public void setFminusoTime(Integer fminusoTime) {
        this.fminusoTime = fminusoTime;
    }

    public Integer getLminusfTime() {
        return lminusfTime;
    }

    public void setLminusfTime(Integer lminusfTime) {
        this.lminusfTime = lminusfTime;
    }

    public Integer getSminushTime() {
        return sminushTime;
    }

    public void setSminushTime(Integer sminushTime) {
        this.sminushTime = sminushTime;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki == null ? null : wiki.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerMis() {
        return ownerMis;
    }

    public void setOwnerMis(String ownerMis) {
        this.ownerMis = ownerMis == null ? null : ownerMis.trim();
    }

    public String getQaName() {
        return qaName;
    }

    public void setQaName(String qaName) {
        this.qaName = qaName == null ? null : qaName.trim();
    }

    public String getQaMis() {
        return qaMis;
    }

    public void setQaMis(String qaMis) {
        this.qaMis = qaMis == null ? null : qaMis.trim();
    }

    public String getCoeLink() {
        return coeLink;
    }

    public void setCoeLink(String coeLink) {
        this.coeLink = coeLink == null ? null : coeLink.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public BigDecimal getRdShare() {
        return rdShare;
    }

    public void setRdShare(BigDecimal rdShare) {
        this.rdShare = rdShare;
    }

    public BigDecimal getQaShare() {
        return qaShare;
    }

    public void setQaShare(BigDecimal qaShare) {
        this.qaShare = qaShare;
    }

    public Boolean getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(Boolean joinStatus) {
        this.joinStatus = joinStatus;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance == null ? null : appearance.trim();
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory == null ? null : subCategory.trim();
    }

    public Integer getAllTodo() {
        return allTodo;
    }

    public void setAllTodo(Integer allTodo) {
        this.allTodo = allTodo;
    }

    public Integer getNotFinishTodo() {
        return notFinishTodo;
    }

    public void setNotFinishTodo(Integer notFinishTodo) {
        this.notFinishTodo = notFinishTodo;
    }

    public Integer getFinishTodo() {
        return finishTodo;
    }

    public void setFinishTodo(Integer finishTodo) {
        this.finishTodo = finishTodo;
    }

    public String getNotFinishTodoTask() {
        return notFinishTodoTask;
    }

    public void setNotFinishTodoTask(String notFinishTodoTask) {
        this.notFinishTodoTask = notFinishTodoTask == null ? null : notFinishTodoTask.trim();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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
        CoeListP0 other = (CoeListP0) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCoeId() == null ? other.getCoeId() == null : this.getCoeId().equals(other.getCoeId()))
            && (this.getBrief() == null ? other.getBrief() == null : this.getBrief().equals(other.getBrief()))
            && (this.getOccurDate() == null ? other.getOccurDate() == null : this.getOccurDate().equals(other.getOccurDate()))
            && (this.getNotifyTime() == null ? other.getNotifyTime() == null : this.getNotifyTime().equals(other.getNotifyTime()))
            && (this.getFindTime() == null ? other.getFindTime() == null : this.getFindTime().equals(other.getFindTime()))
            && (this.getLocationTime() == null ? other.getLocationTime() == null : this.getLocationTime().equals(other.getLocationTime()))
            && (this.getHandleTime() == null ? other.getHandleTime() == null : this.getHandleTime().equals(other.getHandleTime()))
            && (this.getSolvedTime() == null ? other.getSolvedTime() == null : this.getSolvedTime().equals(other.getSolvedTime()))
            && (this.getFminusoTime() == null ? other.getFminusoTime() == null : this.getFminusoTime().equals(other.getFminusoTime()))
            && (this.getLminusfTime() == null ? other.getLminusfTime() == null : this.getLminusfTime().equals(other.getLminusfTime()))
            && (this.getSminushTime() == null ? other.getSminushTime() == null : this.getSminushTime().equals(other.getSminushTime()))
            && (this.getWiki() == null ? other.getWiki() == null : this.getWiki().equals(other.getWiki()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getOwnerName() == null ? other.getOwnerName() == null : this.getOwnerName().equals(other.getOwnerName()))
            && (this.getOwnerMis() == null ? other.getOwnerMis() == null : this.getOwnerMis().equals(other.getOwnerMis()))
            && (this.getQaName() == null ? other.getQaName() == null : this.getQaName().equals(other.getQaName()))
            && (this.getQaMis() == null ? other.getQaMis() == null : this.getQaMis().equals(other.getQaMis()))
            && (this.getCoeLink() == null ? other.getCoeLink() == null : this.getCoeLink().equals(other.getCoeLink()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getRdShare() == null ? other.getRdShare() == null : this.getRdShare().equals(other.getRdShare()))
            && (this.getQaShare() == null ? other.getQaShare() == null : this.getQaShare().equals(other.getQaShare()))
            && (this.getJoinStatus() == null ? other.getJoinStatus() == null : this.getJoinStatus().equals(other.getJoinStatus()))
            && (this.getAppearance() == null ? other.getAppearance() == null : this.getAppearance().equals(other.getAppearance()))
            && (this.getSubCategory() == null ? other.getSubCategory() == null : this.getSubCategory().equals(other.getSubCategory()))
            && (this.getAllTodo() == null ? other.getAllTodo() == null : this.getAllTodo().equals(other.getAllTodo()))
            && (this.getNotFinishTodo() == null ? other.getNotFinishTodo() == null : this.getNotFinishTodo().equals(other.getNotFinishTodo()))
            && (this.getFinishTodo() == null ? other.getFinishTodo() == null : this.getFinishTodo().equals(other.getFinishTodo()))
            && (this.getNotFinishTodoTask() == null ? other.getNotFinishTodoTask() == null : this.getNotFinishTodoTask().equals(other.getNotFinishTodoTask()))
            && (this.getAvailable() == null ? other.getAvailable() == null : this.getAvailable().equals(other.getAvailable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCoeId() == null) ? 0 : getCoeId().hashCode());
        result = prime * result + ((getBrief() == null) ? 0 : getBrief().hashCode());
        result = prime * result + ((getOccurDate() == null) ? 0 : getOccurDate().hashCode());
        result = prime * result + ((getNotifyTime() == null) ? 0 : getNotifyTime().hashCode());
        result = prime * result + ((getFindTime() == null) ? 0 : getFindTime().hashCode());
        result = prime * result + ((getLocationTime() == null) ? 0 : getLocationTime().hashCode());
        result = prime * result + ((getHandleTime() == null) ? 0 : getHandleTime().hashCode());
        result = prime * result + ((getSolvedTime() == null) ? 0 : getSolvedTime().hashCode());
        result = prime * result + ((getFminusoTime() == null) ? 0 : getFminusoTime().hashCode());
        result = prime * result + ((getLminusfTime() == null) ? 0 : getLminusfTime().hashCode());
        result = prime * result + ((getSminushTime() == null) ? 0 : getSminushTime().hashCode());
        result = prime * result + ((getWiki() == null) ? 0 : getWiki().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getOwnerName() == null) ? 0 : getOwnerName().hashCode());
        result = prime * result + ((getOwnerMis() == null) ? 0 : getOwnerMis().hashCode());
        result = prime * result + ((getQaName() == null) ? 0 : getQaName().hashCode());
        result = prime * result + ((getQaMis() == null) ? 0 : getQaMis().hashCode());
        result = prime * result + ((getCoeLink() == null) ? 0 : getCoeLink().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getRdShare() == null) ? 0 : getRdShare().hashCode());
        result = prime * result + ((getQaShare() == null) ? 0 : getQaShare().hashCode());
        result = prime * result + ((getJoinStatus() == null) ? 0 : getJoinStatus().hashCode());
        result = prime * result + ((getAppearance() == null) ? 0 : getAppearance().hashCode());
        result = prime * result + ((getSubCategory() == null) ? 0 : getSubCategory().hashCode());
        result = prime * result + ((getAllTodo() == null) ? 0 : getAllTodo().hashCode());
        result = prime * result + ((getNotFinishTodo() == null) ? 0 : getNotFinishTodo().hashCode());
        result = prime * result + ((getFinishTodo() == null) ? 0 : getFinishTodo().hashCode());
        result = prime * result + ((getNotFinishTodoTask() == null) ? 0 : getNotFinishTodoTask().hashCode());
        result = prime * result + ((getAvailable() == null) ? 0 : getAvailable().hashCode());
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
        sb.append(", brief=").append(brief);
        sb.append(", occurDate=").append(occurDate);
        sb.append(", notifyTime=").append(notifyTime);
        sb.append(", findTime=").append(findTime);
        sb.append(", locationTime=").append(locationTime);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", solvedTime=").append(solvedTime);
        sb.append(", fminusoTime=").append(fminusoTime);
        sb.append(", lminusfTime=").append(lminusfTime);
        sb.append(", sminushTime=").append(sminushTime);
        sb.append(", wiki=").append(wiki);
        sb.append(", level=").append(level);
        sb.append(", ownerName=").append(ownerName);
        sb.append(", ownerMis=").append(ownerMis);
        sb.append(", qaName=").append(qaName);
        sb.append(", qaMis=").append(qaMis);
        sb.append(", coeLink=").append(coeLink);
        sb.append(", category=").append(category);
        sb.append(", rdShare=").append(rdShare);
        sb.append(", qaShare=").append(qaShare);
        sb.append(", joinStatus=").append(joinStatus);
        sb.append(", appearance=").append(appearance);
        sb.append(", subCategory=").append(subCategory);
        sb.append(", allTodo=").append(allTodo);
        sb.append(", notFinishTodo=").append(notFinishTodo);
        sb.append(", finishTodo=").append(finishTodo);
        sb.append(", notFinishTodoTask=").append(notFinishTodoTask);
        sb.append(", available=").append(available);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}