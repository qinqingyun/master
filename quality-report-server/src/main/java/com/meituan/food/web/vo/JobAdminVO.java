package com.meituan.food.web.vo;

import lombok.Data;

import java.util.Date;

@Data

public class JobAdminVO {
    private String jobName;

    private String adminMis;

    private String departmentName;

    private Date updatedTime;
}
