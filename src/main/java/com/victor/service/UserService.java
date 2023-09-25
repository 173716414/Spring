package com.victor.service;

import com.spring.Autowired;
import com.spring.Compoent;
import com.spring.Scope;

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
public class UserService {
    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println(orderService);
    }
}
