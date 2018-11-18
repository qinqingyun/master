package com.meituan.food.web.vo;

import lombok.Data;

@Data
public class CommonResponse<T> {

    private int code;

    private String msg;

    private T data;

    public static CommonResponse errorRes(String msg){
        CommonResponse errorRes=new CommonResponse();
        errorRes.setCode(-1);
        errorRes.setMsg(msg);

        return errorRes;
    }

    public static <T> CommonResponse<T> successRes(String msg,T data){
        CommonResponse<T> successRes=new CommonResponse<>();
        successRes.setCode(0);
        successRes.setMsg(msg);
        successRes.setData(data);

        return successRes;
    }
}
