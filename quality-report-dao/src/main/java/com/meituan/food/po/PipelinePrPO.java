package com.meituan.food.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
public class PipelinePrPO implements Serializable {
    private Integer id;

    private Integer direction_id;

    private String directionName;

    private Integer hotfix;

    private ArrayList<String> hotfixDetail;

    public String getHotfixDetailStr() {
        return hotfixDetailStr;
    }

    public void setHotfixDetailStr(String hotfixDetailStr) {
        this.hotfixDetailStr = hotfixDetailStr;
    }

    private String hotfixDetailStr;

    private Integer skip;

    private ArrayList<String> skipDetail;

    public String getSkipDetailStr() {
        return skipDetailStr;
    }

    public void setSkipDetailStr(String skipDetailStr) {
        this.skipDetailStr = skipDetailStr;
    }

    public String getSkipReasonStr() {
        return skipReasonStr;
    }

    public void setSkipReasonStr(String skipReasonStr) {
        this.skipReasonStr = skipReasonStr;
    }

    private String skipDetailStr;

    private ArrayList<String> skipReason;
    private String skipReasonStr;

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    private LocalDate createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(Integer direction_id) {
        this.direction_id = direction_id;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public Integer getHotfix() {
        return hotfix;
    }

    public void setHotfix(Integer hotfix) {
        this.hotfix = hotfix;
    }

    public ArrayList<String> getHotfixDetail() {
        return hotfixDetail;
    }

    public void setHotfixDetail(ArrayList<String> hotfixDetail) {
        this.hotfixDetail = hotfixDetail;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public ArrayList<String> getSkipDetail() {
        return skipDetail;
    }

    public void setSkipDetail(ArrayList<String> skipDetail) {
        this.skipDetail = skipDetail;
    }

    public ArrayList<String> getSkipReason() {
        return skipReason;
    }

    public void setSkipReason(ArrayList<String> skipReason) {
        this.skipReason = skipReason;
    }




}