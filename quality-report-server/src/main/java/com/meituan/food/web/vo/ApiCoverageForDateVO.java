package com.meituan.food.web.vo;

import lombok.Data;

@Data
public class ApiCoverageForDateVO {
    private String appkey;
    private String apiSpanName;
    private boolean firstDateCoverStatus;
    private boolean secondDateCoverStatus;
}
