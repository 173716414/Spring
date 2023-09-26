package com.victor.service;

import com.spring.*;

/**
 * @Author：Victor_htq
 * @Packag：com.victor.service
 * @Project：Spring
 * @name：UserService
 * @Date：2023/9/14 10:43
 * @Filename：UserService
 */
@Compoent("userService")
// @Scope("prototype")
public class UserService implements BeanNameAware, InitializingBean {
    @Autowired
    private OrderService orderService;

    private String beanName;
    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化");
    }
}
