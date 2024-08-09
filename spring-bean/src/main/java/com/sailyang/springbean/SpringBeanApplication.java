package com.sailyang.springbean;

import org.springframework.aop.scope.DefaultScopedObject;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class SpringBeanApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(SpringBeanApplication.class, args);
        try{
            // 获取国际化资源
            System.out.println(context.getMessage("hi", null, Locale.ENGLISH));

            // 获取资源
            Resource[] resources = context.getResources("classpath:application.yaml");
            for(Resource r : resources) {
                System.out.println(r);
            }

            // 获取环境变量
            System.out.println(context.getEnvironment().getProperty("java_home"));
            System.out.println(context.getEnvironment().getProperty("server.port"));
        } catch ( Exception e) {
            System.out.println(e);
        }


    }

}
