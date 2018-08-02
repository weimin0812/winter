package com.free.winter.version2.test;

import com.free.winter.beans.factory.config.RunTimeBeanReference;
import com.free.winter.beans.factory.support.BeanDefinitionValueResolver;
import com.free.winter.beans.factory.support.DefaultBeanFactory;
import com.free.winter.beans.factory.xml.XmlBeanDefinitionReader;
import com.free.winter.core.io.ClassPathResource;
import com.free.winter.version2.dao.AccountDAO;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author weimin02
 * @date 2018/8/1
 * @project winter
 */
public class BeanDefinitionValueResolverTest {
    @Test
    public void testResolveRuntimeBeanReference() {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

        BeanDefinitionValueResolver beanDefinitionValueResolver = new BeanDefinitionValueResolver(defaultBeanFactory);
        RunTimeBeanReference runTimeBeanReference = new RunTimeBeanReference("accountDAO");
        Object value = beanDefinitionValueResolver.resolveValueIfNecessary(runTimeBeanReference);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDAO);


    }
}
