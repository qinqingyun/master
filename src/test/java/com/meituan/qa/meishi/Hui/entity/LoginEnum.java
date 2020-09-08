package com.meituan.qa.meishi.Hui.entity;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum LoginEnum {
    NEW_ONLY(1, "单写新"),
    NEW_MAIN(2, "新为主"),
    OLD_MAIN(3, "老为主"),
    UNKNOWN(-1, "老为主");

    private final int code;
    private final String text;

    LoginEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static LoginEnum getByName(String name) {
        LoginEnum loginEnum = LoginEnum.valueOf(name);
        if (Objects.isNull(loginEnum)){
            return UNKNOWN;
        }
        return loginEnum;
    }
}



