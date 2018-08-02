package com.free.winter.beans.factory;

import com.free.winter.beans.BeanDefinition;

/**
 * @author weimin02
 * @date 2018/7/30
 * @project winter
 */
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String beanID);

    void registerBeanDefinition(String id, BeanDefinition beanDefinition);
}

