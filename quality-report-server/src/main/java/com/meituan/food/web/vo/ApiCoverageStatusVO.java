package com.meituan.food.web.vo;

import lombok.Data;


@Data
public class ApiCoverageStatusVO {
    private String apiSpanName;
   // private int callCount;
    private boolean core;
    private boolean cover;

}
