package com.meituan.food.extract.impl;

import lombok.Data;

import java.util.Date;
import java.util.List;
/**
 * @author qinqingyun
 * @date 2020/7/22 5:55 PM
 */

@Data
public class COEIneligibleVo {

    private String  brief;
    private Date   create_time;
    private int  totalTime;
    private String  owner_name;
    private String missInformation;

}
