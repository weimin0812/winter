package com.free.winter.beans.factory.config;

/**
 * @author weimin02
 * @date 2018/8/1
 * @project winter
 */
public class RunTimeBeanReference {
    private final String beanName;

    public RunTimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
