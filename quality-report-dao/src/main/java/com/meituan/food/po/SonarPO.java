package com.meituan.food.po;

import java.io.Serializable;
import java.util.Date;

public class SonarPO implements Serializable {
    private Integer id;

    private String project;

    private String includesubproject;

    private Integer blocker;

    private Integer critical;

    private String leader;

    private Date createdAt;

    private Date updatedAt;

    private String link;

    private String sonarDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getIncludesubproject() {
        return includesubproject;
    }

    public void setIncludesubproject(String includesubproject) {
        this.includesubproject = includesubproject == null ? null : includesubproject.trim();
    }

    public Integer getBlocker() {
        return blocker;
    }

    public void setBlocker(Integer blocker) {
        this.blocker = blocker;
    }

    public Integer getCritical() {
        return critical;
    }

    public void setCritical(Integer critical) {
        this.critical = critical;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getSonarDate() {
        return sonarDate;
    }

    public void setSonarDate(String sonarDate) {
        this.sonarDate = sonarDate == null ? null : sonarDate.trim();
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
        SonarPO other = (SonarPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProject() == null ? other.getProject() == null : this.getProject().equals(other.getProject()))
            && (this.getIncludesubproject() == null ? other.getIncludesubproject() == null : this.getIncludesubproject().equals(other.getIncludesubproject()))
            && (this.getBlocker() == null ? other.getBlocker() == null : this.getBlocker().equals(other.getBlocker()))
            && (this.getCritical() == null ? other.getCritical() == null : this.getCritical().equals(other.getCritical()))
            && (this.getLeader() == null ? other.getLeader() == null : this.getLeader().equals(other.getLeader()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getLink() == null ? other.getLink() == null : this.getLink().equals(other.getLink()))
            && (this.getSonarDate() == null ? other.getSonarDate() == null : this.getSonarDate().equals(other.getSonarDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProject() == null) ? 0 : getProject().hashCode());
        result = prime * result + ((getIncludesubproject() == null) ? 0 : getIncludesubproject().hashCode());
        result = prime * result + ((getBlocker() == null) ? 0 : getBlocker().hashCode());
        result = prime * result + ((getCritical() == null) ? 0 : getCritical().hashCode());
        result = prime * result + ((getLeader() == null) ? 0 : getLeader().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getLink() == null) ? 0 : getLink().hashCode());
        result = prime * result + ((getSonarDate() == null) ? 0 : getSonarDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", project=").append(project);
        sb.append(", includesubproject=").append(includesubproject);
        sb.append(", blocker=").append(blocker);
        sb.append(", critical=").append(critical);
        sb.append(", leader=").append(leader);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", link=").append(link);
        sb.append(", sonarDate=").append(sonarDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}