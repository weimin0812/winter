package com.free.winter.version3.test;

import com.free.winter.beans.BeanDefinition;
import com.free.winter.beans.factory.support.ConstructorResolver;
import com.free.winter.beans.factory.support.DefaultBeanFactory;
import com.free.winter.beans.factory.xml.XmlBeanDefinitionReader;
import com.free.winter.core.io.ClassPathResource;
import com.free.winter.core.io.Resource;
import com.free.winter.version3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author weimin02
 * @date 2018/8/3
 * @project winter
 */
public class ConstructorResolverTest {
    @Test
    public void testAutowireConstructor() {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource("petstore-v3.xml");
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        BeanDefinition beanDefinition = defaultBeanFactory.getBeanDefinition("petStore");

        ConstructorResolver constructorResolver = new ConstructorResolver(defaultBeanFactory);
        PetStoreService petStoreService = (PetStoreService) constructorResolver.autowireConstructor(beanDefinition);

        Assert.assertEquals(1, petStoreService.getVersion());
        Assert.assertNotNull(petStoreService.getItemDAO());
        Assert.assertNotNull(petStoreService.getAccountDAO());
    }
}
