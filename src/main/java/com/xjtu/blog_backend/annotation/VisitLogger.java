package com.xjtu.blog_backend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录访客访问日志
 *
 * @Author: Huwwww
 * @Date: 2023/4/15 18:10
 **/
@Target(ElementType.METHOD) //表示该注解可以标记在方法上，即只能用于方法级别的注解，在其他地方使用会报错
@Retention(RetentionPolicy.RUNTIME) //指定了该注解的生命周期是运行时，可以通过反射获取到注解信息
public @interface VisitLogger {
    //访问行为
    String behavior() default "";

    //访问内容
    String content() default "";
}
