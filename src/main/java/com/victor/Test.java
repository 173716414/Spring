package com.victor;

import com.spring.CompoentScan;
import com.spring.VictorApplicationContext;
import com.victor.service.UserService;

/**
 * @Author：Victor_htq
 * @Package：com.victor
 * @Project：Spring
 * @name：Test
 * @Date：2023/9/14 10:11
 * @Filename：Test
 */
@CompoentScan("com.victor.service")
public class Test {
    public static void main(String[] args) {
        VictorApplicationContext applicationContext = new VictorApplicationContext(AppConfig.class);

        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();

        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
        System.out.println(applicationContext.getBean("userService"));
    }
}
