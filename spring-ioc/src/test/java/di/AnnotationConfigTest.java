package di;

import com.sailyang.di.annotationConfig.Bean2;
import com.sailyang.di.annotationConfig.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangfan
 * @version 1.0
 * @description: AnnotationConfigApplicationContext
 * @date 2024/8/26 15:21
 */
public class AnnotationConfigTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        for(String beanNeam : context.getBeanDefinitionNames()){
            System.out.println(beanNeam);
        }
        System.out.println(context.getBean(Bean2.class).getBean1());
        /*
        输出解析
        1. 下面这些是工具类，主要是后置处理器，解析各种注解
        org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        org.springframework.context.annotation.internalCommonAnnotationProcessor
        org.springframework.context.event.internalEventListenerProcessor
        org.springframework.context.event.internalEventListenerFactory
        2. 注入的Bean，配置类也是Bean
        config
        bean1
        bean2
        3. 依赖注入成功
        com.sailyang.di.annotationConfig.Bean1@47eaca72
         */
    }
}
