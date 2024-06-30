package com.sailyang.service;

import com.sailyang.spring.BeanPostProcessor;
import com.sailyang.spring.Component;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/6/30 9:59
 */
@Component
public class YangfanBeanPostProcessor implements BeanPostProcessor {
    @Override
    public void postProcessBeforeInitialization(String beanName, Object bean) {
        if(beanName.equals("userService")) {
            System.out.println("Before userService initial");
        }
    }

    @Override
    public void postProcessAfterInitialization(String beanName, Object bean) {
        if(beanName.equals("userService")) {
            System.out.println("After userService initial");
        }
    }
}
