package com.meituan.food.extract;

import com.sankuai.meituan.org.queryservice.exception.MDMThriftException;

import java.time.LocalDate;

public interface IMailDataExtract {

    void extractMailData4EffDay(LocalDate day) throws MDMThriftException;

}
