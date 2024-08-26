package com.sailyang.di.annotationConfig;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/26 15:27
 */
public class Bean2 {
    private Bean1 bean1;
    public void setBean1(Bean1 bean1){
        this.bean1 = bean1;
    }

    public Bean1 getBean1(){
        return this.bean1;
    }
}
