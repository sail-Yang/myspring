package com.sailyang.springbean.beanLifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yangfan
 * @version 1.0
 * @description: Bean生命周期的各个阶段
 * @date 2024/8/26 15:44
 */
@Component
@Slf4j
public class LifeCycleBean {
    public LifeCycleBean() {
        log.debug("构造Bean");
    }

    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String home) {
        log.debug("依赖注入：{}", home);
    }

    @PostConstruct
    public void init() {
        log.debug("初始化");
    }

    @PreDestroy
    public void destroy() {
        log.debug("销毁");
    }
}
