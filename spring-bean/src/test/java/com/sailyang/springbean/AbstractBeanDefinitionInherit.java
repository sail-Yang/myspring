package com.sailyang.springbean;

import com.sailyang.springbean.ck.HanXin;
import com.sailyang.springbean.ck.LiBai;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/12 9:16
 */
@SpringBootTest
public class AbstractBeanDefinitionInherit {
    @Test
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AbstractBeanDefinitionInherit.class);
        //注册父类：刺客的Bean Definition
        RootBeanDefinition ckBeanDefinition = new RootBeanDefinition();
        ckBeanDefinition.setAbstract(true);
        ckBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        ckBeanDefinition.getPropertyValues().add("type","刺客");
        applicationContext.registerBeanDefinition("ck",ckBeanDefinition);

        //注册刺客李白 child bean definition
        ChildBeanDefinition liBaiBeanDefinition = new ChildBeanDefinition("ck");
        liBaiBeanDefinition.getPropertyValues().add("name","李白");
        liBaiBeanDefinition.setBeanClass(LiBai.class);
        applicationContext.registerBeanDefinition("liBai",liBaiBeanDefinition);

        //注册刺客韩信 generic bean definition
        GenericBeanDefinition hanXinBeanDefinition = new GenericBeanDefinition();
        hanXinBeanDefinition.setParentName("ck");
        hanXinBeanDefinition.getPropertyValues().add("name","韩信");
        hanXinBeanDefinition.setBeanClass(HanXin.class);
        applicationContext.registerBeanDefinition("hanXin",hanXinBeanDefinition);

        //刷新容器
        applicationContext.refresh();
        LiBai liBai = applicationContext.getBean(LiBai.class);
        liBai.selfIntroduce();
        HanXin hanXin = applicationContext.getBean(HanXin.class);
        hanXin.selfIntroduce();

    }
}
