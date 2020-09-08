package com.meituan.qa.meishi.Hui.entity.model;

import com.dianping.hui.common.enums.UserType;
import com.meituan.qa.meishi.Hui.entity.OrderSourceEnum;
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
    String merchantBsid;
    public UserModel(){}
}
