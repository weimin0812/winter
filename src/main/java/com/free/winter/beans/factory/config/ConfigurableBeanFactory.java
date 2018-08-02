package com.free.winter.beans.factory.config;

import com.free.winter.beans.factory.BeanFactory;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    void setBeanClassLoader(ClassLoader beanClassLoader);
    ClassLoader getBeanClassLoader();
}
