package com.meituan.food.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PipelineDirectionPO implements Serializable {
    Integer id;
    Integer direction_id;
    String direction_name;
    String group_name;

}