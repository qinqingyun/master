package com.meituan.food.extract;

public interface IReleaseNameExtract {
    void sync();

    void insertReleaseName(String srv);
}
