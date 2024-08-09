package com.sailyang.springbean;

import org.springframework.aop.scope.DefaultScopedObject;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication
public class SpringBeanApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(SpringBeanApplication.class, args);
        try{
            Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
            singletonObjects.setAccessible(true);
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            Map<String,Object> stringObjectMap = (Map<String,Object>)singletonObjects.get(beanFactory);
//            stringObjectMap.forEach((k,v)->{
//                System.out.println(k+" : "+v);
//            });
            stringObjectMap.entrySet().stream().filter(e->e.getKey().startsWith("component"))
                    .forEach(e->{
                System.out.println(e.getKey()+" : "+e.getValue());
            });
        } catch ( Exception e) {
            System.out.println(e);
        }


    }

}
