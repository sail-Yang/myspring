package com.sailyang.springbean.beanDefine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author yangfan
 * @version 1.0
 * @description: 注册组件
 * @date 2024/8/9 9:13
 */
@Component
public class RegisterComponent {
    private static final Logger log = LoggerFactory.getLogger(RegisterComponent.class);

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    //告诉spring这是一个事件监听的地方

    //注册后发布已注册事件
    public void register() {
        log.debug("registered");
        eventPublisher.publishEvent(new UserRegisteredEvent(RegisterComponent.class));
    }
}
