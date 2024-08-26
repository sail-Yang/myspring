package com.sailyang.springbean.beanDefine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
