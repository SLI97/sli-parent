package com.sli.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)  //注解用于什么地方
@Retention(RetentionPolicy.RUNTIME)  //–什么时候使用该注解
@Documented  // –注解是否将包含在JavaDoc中
@Inherited  //– 是否允许子类继承该注解
public @interface SysLog {
    String value() default "";
}