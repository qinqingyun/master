package com.meituan.food.web.vo;

import lombok.Data;

import java.util.List;

@Data
public class AppkeyVO {
    private String appkey;
    private String adminName;
    private String adminMis;
    private int rank;
    private List<ApiVO> apiVOList;
}
