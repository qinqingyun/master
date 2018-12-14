package com.meituan.food.job.vo;

import java.util.List;

public class SonarGroup {
    String groupName;
    List<String>  subGroupName;
    String leaderName;

    public SonarGroup(){

    }

    public SonarGroup(String groupName,List<String>  subGroupName,String leaderName){
        this.groupName=groupName;
        this.subGroupName=subGroupName;
        this.leaderName=leaderName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "SonarGroup{" +
                "groupName='" + groupName + '\'' +
                ", subGroupName=" + subGroupName +
                ", leaderName='" + leaderName + '\'' +
                '}';
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getSubGroupName() {
        return subGroupName;
    }

    public void setSubGroupName(List<String> subGroupName) {
        this.subGroupName = subGroupName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }
}
