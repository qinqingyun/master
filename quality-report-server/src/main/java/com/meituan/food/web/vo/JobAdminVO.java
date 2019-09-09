package com.meituan.food.web.vo;

import lombok.Data;

import java.util.Date;

@Data
public class JobAdminVO {
    private String jobName;

    private String adminName;

    private Integer departmentId;

    private String departmentName;

    private Date updatedTime;
}
