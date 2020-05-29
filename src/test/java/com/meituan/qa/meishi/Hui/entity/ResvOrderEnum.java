package com.meituan.qa.meishi.Hui.entity;

/**
 * Created by buyuqi on 2019/12/14.
 */
public enum ResvOrderEnum {

    待支付(1),
    新建(10);

    private final int value;

    private ResvOrderEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ResvOrderEnum findByValue(int value) {
        switch(value) {
            case 1:
                return 待支付;
            case 2:
                return 新建;
            default:
                return null;
        }
    }
}
