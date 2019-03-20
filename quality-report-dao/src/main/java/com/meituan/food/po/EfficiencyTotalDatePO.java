package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class EfficiencyTotalDatePO implements Serializable {
    private Integer id;

    private String mis;

    private String name;

    private Long createWikiNum;

    private Long updateWikiNum;

    private Integer gitIncrease;

    private Integer gitDelete;

    private Integer gitSubmit;

    private Integer gitSubmitTime;

    private Integer createBugNum;

    private Integer acceptBugNum;


    private Long sumCreateWikiNum;

    private Long sumUpdateWikiNum;

    private Integer sumGitIncrease;

    private Integer sumGitDelete;

    private Integer sumGitSubmit;

    private Integer sumGitSubmitTime;

    private Integer sumCreateBugNum;

    private Integer sumAcceptBugNum;


    private String partitionDate;

    private Date createdAt;

    private String orgName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMis() {
        return mis;
    }

    public void setMis(String mis) {
        this.mis = mis == null ? null : mis.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getCreateWikiNum() {
        return createWikiNum;
    }

    public void setCreateWikiNum(Long createWikiNum) {
        this.createWikiNum = createWikiNum;
    }

    public Long getUpdateWikiNum() {
        return updateWikiNum;
    }

    public void setUpdateWikiNum(Long updateWikiNum) {
        this.updateWikiNum = updateWikiNum;
    }

    public Integer getGitIncrease() {
        return gitIncrease;
    }

    public void setGitIncrease(Integer gitIncrease) {
        this.gitIncrease = gitIncrease;
    }

    public Integer getGitDelete() {
        return gitDelete;
    }

    public void setGitDelete(Integer gitDelete) {
        this.gitDelete = gitDelete;
    }

    public Integer getGitSubmit() {
        return gitSubmit;
    }

    public void setGitSubmit(Integer gitSubmit) {
        this.gitSubmit = gitSubmit;
    }

    public Integer getGitSubmitTime() {
        return gitSubmitTime;
    }

    public void setGitSubmitTime(Integer gitSubmitTime) {
        this.gitSubmitTime = gitSubmitTime;
    }

    public Integer getCreateBugNum() {
        return createBugNum;
    }

    public void setCreateBugNum(Integer createBugNum) {
        this.createBugNum = createBugNum;
    }

    public Integer getAcceptBugNum() {
        return acceptBugNum;
    }

    public void setAcceptBugNum(Integer acceptBugNum) {
        this.acceptBugNum = acceptBugNum;
    }


    public Long getSumCreateWikiNum() {
        return sumCreateWikiNum;
    }

    public void setSumCreateWikiNum(Long sumCreateWikiNum) {
        this.sumCreateWikiNum = sumCreateWikiNum;
    }

    public Long getSumUpdateWikiNum() {
        return sumUpdateWikiNum;
    }

    public void setSumUpdateWikiNum(Long sumUpdateWikiNum) {
        this.sumUpdateWikiNum = sumUpdateWikiNum;
    }

    public Integer getSumGitIncrease() {
        return sumGitIncrease;
    }

    public void setSumGitIncrease(Integer sumGitIncrease) {
        this.sumGitIncrease = sumGitIncrease;
    }

    public Integer getSumGitDelete() {
        return sumGitDelete;
    }

    public void setSumGitDelete(Integer sumGitDelete) {
        this.sumGitDelete = sumGitDelete;
    }

    public Integer getSumGitSubmit() {
        return sumGitSubmit;
    }

    public void setSumGitSubmit(Integer sumGitSubmit) {
        this.sumGitSubmit = sumGitSubmit;
    }

    public Integer getSumGitSubmitTime() {
        return sumGitSubmitTime;
    }

    public void setSumGitSubmitTime(Integer sumGitSubmitTime) {
        this.sumGitSubmitTime = sumGitSubmitTime;
    }

    public Integer getSumCreateBugNum() {
        return sumCreateBugNum;
    }

    public void setSumCreateBugNum(Integer sumCreateBugNum) {
        this.sumCreateBugNum = sumCreateBugNum;
    }

    public Integer getSumAcceptBugNum() {
        return sumAcceptBugNum;
    }

    public void setSumAcceptBugNum(Integer sumAcceptBugNum) {
        this.sumAcceptBugNum = sumAcceptBugNum;
    }


    public String getPartitionDate() {
        return partitionDate;
    }

    public void setPartitionDate(String partitionDate) {
        this.partitionDate = partitionDate == null ? null : partitionDate.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
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
        EfficiencyTotalDatePO other = (EfficiencyTotalDatePO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMis() == null ? other.getMis() == null : this.getMis().equals(other.getMis()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCreateWikiNum() == null ? other.getCreateWikiNum() == null : this.getCreateWikiNum().equals(other.getCreateWikiNum()))
            && (this.getUpdateWikiNum() == null ? other.getUpdateWikiNum() == null : this.getUpdateWikiNum().equals(other.getUpdateWikiNum()))
            && (this.getGitIncrease() == null ? other.getGitIncrease() == null : this.getGitIncrease().equals(other.getGitIncrease()))
            && (this.getGitDelete() == null ? other.getGitDelete() == null : this.getGitDelete().equals(other.getGitDelete()))
            && (this.getGitSubmit() == null ? other.getGitSubmit() == null : this.getGitSubmit().equals(other.getGitSubmit()))
            && (this.getGitSubmitTime() == null ? other.getGitSubmitTime() == null : this.getGitSubmitTime().equals(other.getGitSubmitTime()))
            && (this.getCreateBugNum() == null ? other.getCreateBugNum() == null : this.getCreateBugNum().equals(other.getCreateBugNum()))
            && (this.getAcceptBugNum() == null ? other.getAcceptBugNum() == null : this.getAcceptBugNum().equals(other.getAcceptBugNum()))
            && (this.getPartitionDate() == null ? other.getPartitionDate() == null : this.getPartitionDate().equals(other.getPartitionDate()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getOrgName() == null ? other.getOrgName() == null : this.getOrgName().equals(other.getOrgName()))

            && (this.getSumCreateWikiNum() == null ? other.getSumCreateWikiNum() == null : this.getSumCreateWikiNum().equals(other.getSumCreateWikiNum()))
            && (this.getSumUpdateWikiNum() == null ? other.getSumUpdateWikiNum() == null : this.getSumUpdateWikiNum().equals(other.getSumUpdateWikiNum()))
            && (this.getSumGitIncrease() == null ? other.getSumGitIncrease() == null : this.getSumGitIncrease().equals(other.getSumGitIncrease()))
            && (this.getSumGitDelete() == null ? other.getSumGitDelete() == null : this.getSumGitDelete().equals(other.getSumGitDelete()))
            && (this.getSumGitSubmit() == null ? other.getSumGitSubmit() == null : this.getSumGitSubmit().equals(other.getSumGitSubmit()))
            && (this.getSumGitSubmitTime() == null ? other.getSumGitSubmitTime() == null : this.getSumGitSubmitTime().equals(other.getSumGitSubmitTime()))
            && (this.getSumCreateBugNum() == null ? other.getSumCreateBugNum() == null : this.getSumCreateBugNum().equals(other.getSumCreateBugNum()))
            && (this.getSumAcceptBugNum() == null ? other.getSumAcceptBugNum() == null : this.getSumAcceptBugNum().equals(other.getSumAcceptBugNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMis() == null) ? 0 : getMis().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreateWikiNum() == null) ? 0 : getCreateWikiNum().hashCode());
        result = prime * result + ((getUpdateWikiNum() == null) ? 0 : getUpdateWikiNum().hashCode());
        result = prime * result + ((getGitIncrease() == null) ? 0 : getGitIncrease().hashCode());
        result = prime * result + ((getGitDelete() == null) ? 0 : getGitDelete().hashCode());
        result = prime * result + ((getGitSubmit() == null) ? 0 : getGitSubmit().hashCode());
        result = prime * result + ((getGitSubmitTime() == null) ? 0 : getGitSubmitTime().hashCode());
        result = prime * result + ((getCreateBugNum() == null) ? 0 : getCreateBugNum().hashCode());
        result = prime * result + ((getAcceptBugNum() == null) ? 0 : getAcceptBugNum().hashCode());
        result = prime * result + ((getPartitionDate() == null) ? 0 : getPartitionDate().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getOrgName() == null) ? 0 : getOrgName().hashCode());

        result = prime * result + ((getSumCreateWikiNum() == null) ? 0 : getSumCreateWikiNum().hashCode());
        result = prime * result + ((getSumUpdateWikiNum() == null) ? 0 : getSumUpdateWikiNum().hashCode());
        result = prime * result + ((getSumGitIncrease() == null) ? 0 : getSumGitIncrease().hashCode());
        result = prime * result + ((getSumGitDelete() == null) ? 0 : getSumGitDelete().hashCode());
        result = prime * result + ((getSumGitSubmit() == null) ? 0 : getSumGitSubmit().hashCode());
        result = prime * result + ((getSumGitSubmitTime() == null) ? 0 : getSumGitSubmitTime().hashCode());
        result = prime * result + ((getSumCreateBugNum() == null) ? 0 : getSumCreateBugNum().hashCode());
        result = prime * result + ((getSumAcceptBugNum() == null) ? 0 : getSumAcceptBugNum().hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mis=").append(mis);
        sb.append(", name=").append(name);
        sb.append(", createWikiNum=").append(createWikiNum);
        sb.append(", updateWikiNum=").append(updateWikiNum);
        sb.append(", gitIncrease=").append(gitIncrease);
        sb.append(", gitDelete=").append(gitDelete);
        sb.append(", gitSubmit=").append(gitSubmit);
        sb.append(", gitSubmitTime=").append(gitSubmitTime);
        sb.append(", createBugNum=").append(createBugNum);
        sb.append(", acceptBugNum=").append(acceptBugNum);
        sb.append(", partitionDate=").append(partitionDate);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", orgName=").append(orgName);

        sb.append(", sumCreateWikiNum=").append(sumCreateWikiNum);
        sb.append(", sumUpdateWikiNum=").append(sumUpdateWikiNum);
        sb.append(", sumGitIncrease=").append(sumGitIncrease);
        sb.append(", sumGitDelete=").append(sumGitDelete);
        sb.append(", sumGitSubmit=").append(sumGitSubmit);
        sb.append(", sumGitSubmitTime=").append(sumGitSubmitTime);
        sb.append(", sumCreateBugNum=").append(sumCreateBugNum);
        sb.append(", sumAcceptBugNum=").append(sumAcceptBugNum);

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}