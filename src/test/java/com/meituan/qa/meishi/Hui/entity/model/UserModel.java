package com.meituan.qa.meishi.Hui.entity.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liyuhua on 01/04/2020.
 */
@Getter
@Setter
public class UserModel {
    String token;
    String userAgent;
    String userId;
    public UserModel(){}
}
