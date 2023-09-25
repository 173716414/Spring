package com.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author：Victor_htq
 * @Package：com.spring
 * @Project：Spring
 * @name：CompoentScan
 * @Date：2023/9/14 10:16
 * @Filename：CompoentScan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {

    String value();
}
