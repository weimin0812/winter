package com.free.winter.context.support;

import com.free.winter.beans.factory.support.DefaultBeanFactory;
import com.free.winter.beans.factory.xml.XmlBeanDefinitionReader;
import com.free.winter.context.ApplicationContext;
import com.free.winter.core.io.Resource;
import com.free.winter.util.ClassUtils;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory defaultBeanFactory = null;
    private ClassLoader beanClassLoader;


    public AbstractApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = this.getResourceByPath(configFile);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        defaultBeanFactory.setBeanClassLoader(beanClassLoader);
    }

    protected abstract Resource getResourceByPath(String configFile);


    @Override
    public Object getBean(String beanID) {
        return defaultBeanFactory.getBean(beanID);
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : beanClassLoader;
    }
}
