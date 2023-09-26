package com.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

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

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>(); //单例池
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(); //单例池
    public VictorApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 解析配置类
        scan(configClass);

        for (String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    public Object createBean(String beanName, BeanDefinition beanDefinition) {

        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // 依赖注入

            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {

                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }
            // aware回调
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware)instance).setBeanName(beanName);
            }

            // 初始化
            if (instance instanceof InitializingBean) {
                try {
                    ((InitializingBean)instance).afterPropertiesSet();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return instance;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        // return null;
    }
    private void scan(Class configClass) {
        //    解析配置类
        //    ComponentScan注解--->扫描路径--->扫描
        CompoentScan compoentScanAnnotation = (CompoentScan) configClass.getDeclaredAnnotation(CompoentScan.class);
        String path = compoentScanAnnotation.value(); //扫描路径
        path = path.replace(".", "/");
        // System.out.println(path);

        // 扫描
        // Bootstrap ---> jre/lib
        // Ext ---> jre/ext/lib
        // App ---> classpath
        ClassLoader classLoader = VictorApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        // System.out.println(resource);
        File file = new File(resource.getFile());
        // System.out.println(file.isDirectory());
        if (file.isDirectory()) {
            // System.out.println(file);
            File[] files = file.listFiles();
            for (File f : files) {
                // System.out.println(f);
                String filename = f.getAbsolutePath();
                if (filename.endsWith(".class")) {
                    String className = filename.substring(filename.indexOf("com"), filename.indexOf(".class"));
                    className = className.replace("\\", ".");
                    // System.out.println(className);
                    
                    try {
                        Class<?> clazz = null;
                        clazz = classLoader.loadClass(className);
                        if (clazz.isAnnotationPresent(Compoent.class)) {
                            // System.out.println(clazz);
                        //     解析类，判断是单例还是原型bean
                        //     BeanDefinition
                            Compoent compoentAnnotation = clazz.getDeclaredAnnotation(Compoent.class);
                            String beanName = compoentAnnotation.value();

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);
                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotation.value());
                            } else {
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    
                }
            }

        }
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                Object o = singletonObjects.get(beanName);
                return o;
            } else {
            //     原型，创建bean对象
                Object bean = createBean(beanName, beanDefinition);
                return bean;
            }
        } else {
            // 不存在对应的bean
            throw  new NullPointerException();
        }

        // return null;
    }
}
