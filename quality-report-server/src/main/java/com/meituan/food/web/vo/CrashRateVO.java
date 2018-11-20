package com.meituan.food.web.vo;

import lombok.Data;

@Data
public class CrashRateVO {

    private String platform;

    private String startDate;

    private String endDate;

    private int crash;

    private String crashRate;
}
