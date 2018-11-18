package com.meituan.food.web.vo;

import lombok.Data;

import java.util.Date;


@Data
public class IssueVO {

    private String brief;

    private String level;

    private String department;

    private String type;

    private String wiki;

    private Date createdAt;

    private Date updatedAt;
}
