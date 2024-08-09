package com.sailyang.springbean;

import org.springframework.context.ApplicationEvent;

/**
 * @author yangfan
 * @version 1.0
 * @description: 用户已注册事件
 * @date 2024/8/9 11:07
 */

public class UserRegisteredEvent extends ApplicationEvent {
    public UserRegisteredEvent(Object source) {
        super(source);
    }
}
