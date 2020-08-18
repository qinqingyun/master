package com.meituan.food.extract.impl;

import lombok.Data;

import java.util.Date;

/**
 * @author qinqingyun
 * @date 2020/7/27 1:20 PM
 */
@Data
public class COETodoVo {

    private String todotitle;
    private String owner_name;
    private Date finishtime;
    private int time;


}
