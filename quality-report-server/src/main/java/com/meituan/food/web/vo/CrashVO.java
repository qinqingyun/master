package com.meituan.food.web.vo;

import lombok.Data;

@Data
public class CrashVO {

    private String plantform;

    //private String os;

    private String startDate;

    private String endDate;

    private int crash;
}
