package com.spring;

/**
 * @Author：Victor_htq
 * @Package：com.spring
 * @Project：Spring
 * @name：InitializingBean
 * @Date：2023/9/26 17:00
 * @Filename：InitializingBean
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
