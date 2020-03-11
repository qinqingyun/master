package com.meituan.food.extract;

import java.time.LocalDate;

/**
 * Created by wjn on 2020-03-03.
 */
public interface IOneDayTpPipelineExtract {

    void UpdateTpPipelineData(LocalDate date);
    void insertDirection(Integer direction_id,String direction_name,String group_name);

}
