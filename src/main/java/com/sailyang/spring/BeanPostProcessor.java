package com.sailyang.spring;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/6/30 9:58
 */
public interface BeanPostProcessor {
    public void postProcessBeforeInitialization(String beanName, Object bean);
    public void postProcessAfterInitialization(String beanName, Object bean);
}
