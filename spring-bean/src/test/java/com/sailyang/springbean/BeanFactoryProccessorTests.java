package com.sailyang.springbean;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangfan
 * @version 1.0
 * @description: 测试BeanFactory后置处理器
 * @date 2024/8/11 9:12
 */
@SpringBootTest
public class BeanFactoryProccessorTests {
    @Test
    public static void main(String[] args) {
        // 向BeanFactory注册Config类
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition configBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config",configBeanDefinition);

        //此时打印只有config，没有Bean1和Bean2
        //因为还没有进行注解的分析
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

        //用后置处理器进行注解的解析
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        //现在beanFactory里注册了很多后置处理器
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

        //找到BeanFactory后置处理器，执行
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });

        //现在beanFactory里已经解析了注解，可以获取到bean1和bean2
//        for (String name : beanFactory.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

        //现在看看Bean1里面的Bean2有没有成功依赖注入
//        Bean1 bean1 = beanFactory.getBean(Bean1.class);
//        System.out.println(bean1.getBean2());

        //结果是没有成功依赖注入，因为Autowired需要Bean后置处理器才能解析，不同于前面的BeanFactory后置处理器
        //Bean后置处理器，针对Bean的生命周期的各个阶段提供扩展，比如@Autowired，@Resource

        //Bean后置处理器实例化
//        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);
//
//        //单例是用到才会实例化，下面这句可以提前实例化Bean
//        beanFactory.preInstantiateSingletons();
//
//        //现在看看Bean1里面的Bean2有没有成功依赖注入
//        Bean1 bean1 = beanFactory.getBean(Bean1.class);
//        System.out.println(bean1.getBean2());

    }

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1(){
            return new Bean1();
        };

        @Bean
        public Bean2 bean2(){
            return new Bean2();
        };
    }


    static class Bean1 {
        private static final Logger log= LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            log.info("build Bean1");
        }

        @Autowired
        private Bean2 bean2;

        private Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);

        public Bean2() {
            log.info("build Bean2");
        }


    }
}


