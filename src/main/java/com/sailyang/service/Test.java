package com.sailyang.service;

import com.sailyang.spring.YangfanApplicationContext;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/6/28 10:17
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        YangfanApplicationContext applicationContext = new YangfanApplicationContext(AppConfig.class);
        UserInterface userService = (UserInterface) applicationContext.getBean("userService");
        userService.test();
    }
}
