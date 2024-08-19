package com.sailyang.di.xml;

/**
 * @author yangfan
 * @version 1.0
 * @description: 橙汁类用setter方式依赖注入
 * @date 2024/8/19 16:58
 */
public class OrangeJuiceSetterDI {
    //引入添加剂参数
    private Additive additive;

    //创建setter方法
    public void setAdditive(Additive additive) {
        this.additive = additive;
    }

    public void needOrangeJucie() {
        //调用加入添加剂方法
        System.out.println("这里是Setter方法:\n消费者点了一杯橙汁，无添加剂");
        additive.addAdditive();
    }
}
