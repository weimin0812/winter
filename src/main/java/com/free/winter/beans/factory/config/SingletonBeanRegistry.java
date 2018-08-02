package com.free.winter.beans.factory.config;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName, Object singletonObject);
    Object getSingleton(String beanName);
}
