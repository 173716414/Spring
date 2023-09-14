package com.spring;

import java.io.File;
import java.net.URL;

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
        CompoentScan compoentScanAnnotation = (CompoentScan)configClass.getDeclaredAnnotation(CompoentScan.class);
        String path = compoentScanAnnotation.value(); //扫描路径
        path = path.replace(".", "/");
        System.out.println(path);

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
                    System.out.println(className);
                    
                    try {
                        Class<?> clazz = null;
                        clazz = classLoader.loadClass(className);
                        if (clazz.isAnnotationPresent(Compoent.class)) {
                            System.out.println(clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    
                }
            }

        }
    }

    public Object getBean(String beanName) {
        return null;
    }
}
