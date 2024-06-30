package com.sailyang.service;

import com.sailyang.spring.*;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/6/28 10:18
 */
@Component
//@Scope("prototype")
public class UserService implements BeanNameAware, InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;

    private String userName;

    public void test() {
        System.out.println(orderService);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("afterPropertiesSet()");
    }
}
