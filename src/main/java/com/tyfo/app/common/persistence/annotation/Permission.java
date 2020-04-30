package com.tyfo.app.common.persistence.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description 权限注解
 * @Author Benjamin
 * @CreateDate 2019-06-10 16:41
 **/
@Retention(RUNTIME)
@Target(METHOD)
public @interface Permission {
    String[] value() default "";
}
