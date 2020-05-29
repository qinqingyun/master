package com.meituan.qa.meishi.util;
import java.lang.annotation.*;

/**
 * Created by liyuhua on 17/07/2019.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassAnnotation {

    public String author() default "";

    //接口名称,http类型为接口path,thrift为服务加方法
    public String apiName() default "";

    //事业部或业务端(外卖C端,外卖B端,外卖M端)
    public String depart() default "";

    //http方法(get,post)thrift接口此项不写
    public String httpMethod() default "";

    //接口类型(http,thrift)
    public String type() default "";

    //接口类型(http,thrift)
    public String des() default "";
}
