package com.meituan.food.extract;

import java.time.LocalDate;

/**
 * Created by wjn on 2020-02-17.
 */
public interface IOneDayPrPipelineExtract {

    void UpdatePrPipelineData(LocalDate date);
    void UpdatePrAutoData(LocalDate date);

}
