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
            RegisterComponent registerComponent = context.getBean(RegisterComponent.class);
            registerComponent.register();
        } catch ( Exception e) {
            System.out.println(e);
        }
    }

}
