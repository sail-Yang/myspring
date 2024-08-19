package com.sailyang.springbean.ck;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/8/12 9:23
 */
public class LiBai implements Hero {
    private String type;
    private String name;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void selfIntroduce() {
        System.out.printf("我是%s，是一名%s\n", name, type);
    }
}
