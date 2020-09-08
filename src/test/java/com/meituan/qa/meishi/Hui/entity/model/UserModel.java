package com.meituan.qa.meishi.Hui.entity.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by buyuqi on 01/07/2020.
 */
@Getter
@Setter
public class UserModel {
    String token;
    String userAgent;
    String userId;
    String dpToken;
    String dpUserAgent;
    String dpUserId;
    String merchantBsid;
    public UserModel(){}
}
