package com.meituan.food.extract.impl;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author qinqingyun
 * @date 2020/7/22 5:20 PM
 */
@Data
public class COEInfoVo {

    private String  brief;
    private String  level;
    private Date   occur_time;
    private String  owner_name;
    private List time;



}
