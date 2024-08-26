package com.sailyang.springbean.beanLifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/26 15:38
 */
@SpringBootApplication
public class LifecycleSpringApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LifecycleSpringApplication.class, args);
        //为了展示lifecycle，这里调用了close方法
        context.close();
    }
}
