package com.meituan.food.po;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GroupCoverageVO {

    private int apiCount;
    private int coverApiCount;
    private BigDecimal apiCoverage;
    private int coreApiCount;
    private int coverCoreApiCount;
    private BigDecimal coreApiCoverage;
    private int departmentId;
    private String department;
    private int departmentId2;
    private String department2;

}
