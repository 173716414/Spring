package com.spring;

/**
 * @Author：Victor_htq
 * @Package：com.spring
 * @Project：Spring
 * @name：VictorApplicationContext
 * @Date：2023/9/14 10:11
 * @Filename：VictorApplicationContext
 */
public class VictorApplicationContext {

    private Class configClass;

    public VictorApplicationContext(Class configClass) {
        this.configClass = configClass;

    //    解析配置类
    //    ComponentScan注解--->扫描路径--->扫描
    }

    public Object getBean(String beanName) {
        return null;
    }
}
