package com.sailyang.spring;

/**
 * @author yangfan
 * @version 1.0
 * @description: TODO
 * @date 2024/6/28 11:05
 */
public class BeanDefinition {
    private Class type;

    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
