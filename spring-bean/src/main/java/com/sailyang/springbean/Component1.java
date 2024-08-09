package com.sailyang.springbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/9 9:13
 */
@Component
public class Component1 {
    private static final Logger log = LoggerFactory.getLogger(Component1.class);

    //告诉spring这是一个事件监听的地方
    @EventListener
    //参数就是发送事件的对象
    public void aaa(UserRegisteredEvent event){
        //方法执行会将这个信息打印到控制台
        log.debug("{}",event);
    }
}
