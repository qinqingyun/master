package com.meituan.food.po;

import java.io.Serializable;

public class GitPO implements Serializable {
    private Integer id;

    private String misid;

    private String name;

    private String gitDate;

    private Integer gitCodeIncrease;

    private Integer gitCodeDelete;

    private Integer gitCodeSubmit;

    private Integer gitCodeSubmitTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMisid() {
        return misid;
    }

    public void setMisid(String misid) {
        this.misid = misid == null ? null : misid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGitDate() {
        return gitDate;
    }

    public void setGitDate(String gitDate) {
        this.gitDate = gitDate == null ? null : gitDate.trim();
    }

    public Integer getGitCodeIncrease() {
        return gitCodeIncrease;
    }

    public void setGitCodeIncrease(Integer gitCodeIncrease) {
        this.gitCodeIncrease = gitCodeIncrease;
    }

    public Integer getGitCodeDelete() {
        return gitCodeDelete;
    }

    public void setGitCodeDelete(Integer gitCodeDelete) {
        this.gitCodeDelete = gitCodeDelete;
    }

    public Integer getGitCodeSubmit() {
        return gitCodeSubmit;
    }

    public void setGitCodeSubmit(Integer gitCodeSubmit) {
        this.gitCodeSubmit = gitCodeSubmit;
    }

    public Integer getGitCodeSubmitTime() {
        return gitCodeSubmitTime;
    }

    public void setGitCodeSubmitTime(Integer gitCodeSubmitTime) {
        this.gitCodeSubmitTime = gitCodeSubmitTime;
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
        GitPO other = (GitPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMisid() == null ? other.getMisid() == null : this.getMisid().equals(other.getMisid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getGitDate() == null ? other.getGitDate() == null : this.getGitDate().equals(other.getGitDate()))
            && (this.getGitCodeIncrease() == null ? other.getGitCodeIncrease() == null : this.getGitCodeIncrease().equals(other.getGitCodeIncrease()))
            && (this.getGitCodeDelete() == null ? other.getGitCodeDelete() == null : this.getGitCodeDelete().equals(other.getGitCodeDelete()))
            && (this.getGitCodeSubmit() == null ? other.getGitCodeSubmit() == null : this.getGitCodeSubmit().equals(other.getGitCodeSubmit()))
            && (this.getGitCodeSubmitTime() == null ? other.getGitCodeSubmitTime() == null : this.getGitCodeSubmitTime().equals(other.getGitCodeSubmitTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMisid() == null) ? 0 : getMisid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getGitDate() == null) ? 0 : getGitDate().hashCode());
        result = prime * result + ((getGitCodeIncrease() == null) ? 0 : getGitCodeIncrease().hashCode());
        result = prime * result + ((getGitCodeDelete() == null) ? 0 : getGitCodeDelete().hashCode());
        result = prime * result + ((getGitCodeSubmit() == null) ? 0 : getGitCodeSubmit().hashCode());
        result = prime * result + ((getGitCodeSubmitTime() == null) ? 0 : getGitCodeSubmitTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", misid=").append(misid);
        sb.append(", name=").append(name);
        sb.append(", gitDate=").append(gitDate);
        sb.append(", gitCodeIncrease=").append(gitCodeIncrease);
        sb.append(", gitCodeDelete=").append(gitCodeDelete);
        sb.append(", gitCodeSubmit=").append(gitCodeSubmit);
        sb.append(", gitCodeSubmitTime=").append(gitCodeSubmitTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}