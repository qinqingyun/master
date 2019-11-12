package com.meituan.food.extract;

import java.time.LocalDate;
import java.util.Date;

public interface INewCrashExtract {

    void sync(LocalDate lastDay);

    void syncForDays(Date startDate, Date endDate);
}
