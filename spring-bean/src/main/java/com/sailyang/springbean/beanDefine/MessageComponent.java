package com.sailyang.springbean.beanDefine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/9 11:17
 */
@Component
public class MessageComponent {
    private  static  final Logger log= LoggerFactory.getLogger(MessageComponent.class);

    @EventListener
    public void sendMessage(UserRegisteredEvent event)
    {
        log.debug("{}",event);
        log.debug("发送短信");
    }
}
