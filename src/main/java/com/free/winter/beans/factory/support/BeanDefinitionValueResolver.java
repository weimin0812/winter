package com.free.winter.beans.factory.support;

import com.free.winter.beans.factory.BeanFactory;
import com.free.winter.beans.factory.config.RunTimeBeanReference;
import com.free.winter.beans.factory.config.TypedStringValue;

/**
 * @author weimin02
 * @date 2018/8/1
 * @project winter
 */
public class BeanDefinitionValueResolver {
    private final BeanFactory defaultBeanFactory;

    public BeanDefinitionValueResolver(BeanFactory defaultBeanFactory) {
        this.defaultBeanFactory = defaultBeanFactory;
    }


    public Object resolveValueIfNecessary(Object object) {
        if (object instanceof RunTimeBeanReference) {
            RunTimeBeanReference runTimeBeanReference = (RunTimeBeanReference) object;
            String refName = runTimeBeanReference.getBeanName();
            Object bean = defaultBeanFactory.getBean(refName);
            return bean;
        } else if (object instanceof TypedStringValue) {
            return ((TypedStringValue) object).getValue();
        } else {
            throw new RuntimeException("the value " + object + " has not implement");
        }
    }
}
