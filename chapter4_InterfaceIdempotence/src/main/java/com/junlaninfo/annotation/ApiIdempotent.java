package com.junlaninfo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 辉 on 2020/3/8.
 * 自定义注解，标记此注解的方法需要注意接口幂等性问题
 */
@Target({ElementType.METHOD})  //作用在方法上
@Retention(RetentionPolicy.RUNTIME)  //在runtime时生效
public @interface ApiIdempotent {
}
