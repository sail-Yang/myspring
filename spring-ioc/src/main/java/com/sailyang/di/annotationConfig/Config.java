package com.sailyang.di.annotationConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/26 15:22
 */
@Configuration
public class Config {
    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public Bean2 bean2(Bean1 bean1) {
        Bean2 bean2 = new Bean2();
        bean2.setBean1(bean1);
        return bean2;
    }


}
