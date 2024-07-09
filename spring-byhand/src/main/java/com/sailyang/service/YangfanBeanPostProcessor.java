package com.sailyang.service;

import com.sailyang.spring.BeanPostProcessor;
import com.sailyang.spring.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/6/30 9:59
 */
@Component
public class YangfanBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if(beanName.equals("userService")) {
            System.out.println("Before userService initial");
        }
        return bean;
    }

    @Override
    public  Object postProcessAfterInitialization(String beanName, Object bean) {
        if(beanName.equals("userService")) {
            Object proxyInstance = Proxy.newProxyInstance(YangfanBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("切面逻辑");
                    return method.invoke(bean, args);
                }
            });
            return proxyInstance;
        }
        return bean;
    }
}
