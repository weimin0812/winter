package com.free.winter.beans.factory.support;

import com.free.winter.beans.factory.config.SingletonBeanRegistry;
import com.free.winter.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();


    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "beanname must not be null");
        Object oldObject = singletonObjects.get(beanName);
        if (oldObject != null){
            throw new IllegalArgumentException("Could not register object {" + singletonObject + " } under bean name " + beanName
            + " there is already object: {" + oldObject + "}");

        }

        singletonObjects.put(beanName, singletonObject);

    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
}
