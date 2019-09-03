package com.meituan.food.web.vo;

import lombok.Data;

import java.util.List;

@Data
public class AppkeyVO {
    private String appkey;
    private String adminName;
    private String adminMis;
//    private boolean isCoreSrv;
    private boolean coreSrv;
    private List<ApiVO> apiVOList;
}
