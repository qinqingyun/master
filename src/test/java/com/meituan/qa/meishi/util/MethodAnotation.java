package com.meituan.qa.meishi.util;
import java.lang.annotation.*;

/**
 * Created by liyuhua on 17/07/2019.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodAnotation {

    //用例维护人
    public String author() default "";

    //用例创建时间,格式 xxxx-xx-xx
    public String createTime() default "";

    //用例更新时间,格式 xxxx-xx-xx
    public String updateTime() default "";

    //用例描述
    public String des() default "";

}
