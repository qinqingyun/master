package com.meituan.food.web.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ApiVO {
    private String appkey;
    private String apiName;
    private int isCore; //核心:1；非核心:0
    private long callCount; //接口调用量
    private BigDecimal proportion; //接口调用量占比
    private Date updatedTime; //数据更新时间
}
