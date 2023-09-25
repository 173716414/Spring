package com.spring;

/**
 * @Author：Victor_htq
 * @Package：com.spring
 * @Project：Spring
 * @name：BeanDefinition
 * @Date：2023/9/25 18:57
 * @Filename：BeanDefinition
 */
public class BeanDefinition {
    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public BeanDefinition(Class clazz, String scope) {
        this.clazz = clazz;
        this.scope = scope;
    }

    private Class clazz;

    public BeanDefinition() {
    }

    private  String scope;
}
