package com.sailyang.springbean.beanLifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yangfan
 * @version 1.0
 * @description: 后置处理器
 * @date 2024/8/26 15:55
 */
@Slf4j
@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor, DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)){
            log.debug("<<<<<<<<< 销毁之前执行，如@PreDestroy");
        }
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)){
            log.debug("<<<<<<<<< 实例化之前执行，这里返回的对象会替换原本的bean");
        }
        return null;
//        return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)){
            log.debug("<<<<<<<<< 实例化之后执行，这里如果返回false会跳过DI阶段");
//            return false;
        }
        return true;
//        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    //主要扩展功能
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)) {
            log.debug("<<<<<<<<< DI阶段，如@Autowired,@Value，@Resource");
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)) {
            log.debug("<<<<<<<<< 初始化之前执行，这里返回的对象会替换原本的Bean，如@PostConstruct");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("lifeCycleBean".equals(beanName)) {
            log.debug("<<<<<<<<< 初始化之后执行，这里返回的对象会替换原本的Bean，如代理增强");
        }
        return bean;
    }
}
