package com.sailyang.di.xml;

/**
 * @author yangfan
 * @version 1.0
 * @description: 橙汁类用构造器方式依赖注入
 * @date 2024/8/19 16:51
 */
public class OrangeJuiceConstructorDI {
    private Additive additive;

    //创建有参构造函数
    public OrangeJuiceConstructorDI(Additive additive) {
        this.additive = additive;
    }
    public void needOrangeJuice() {
        //调用加入添加剂方法
        System.out.println("这里是Constructor方法:\n消费者点了一杯橙汁，无添加剂");
        additive.addAdditive();
    }
}
