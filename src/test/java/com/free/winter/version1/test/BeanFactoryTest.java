package com.free.winter.version1.test;

import com.free.winter.beans.BeanDefinition;
import com.free.winter.beans.factory.BeanCreationException;
import com.free.winter.beans.factory.BeanDefinitionStoreException;
import com.free.winter.beans.factory.BeanFactory;
import com.free.winter.beans.factory.support.DefaultBeanFactory;
import com.free.winter.beans.factory.xml.XmlBeanDefinitionReader;
import com.free.winter.core.io.ClassPathResource;
import com.free.winter.core.io.Resource;
import com.free.winter.version1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author weimin02
 * @date 2018/7/28
 * @project winter
 */
public class BeanFactoryTest {
    DefaultBeanFactory beanFactory = null;
    XmlBeanDefinitionReader xmlBeanDefinitionReader = null;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
    }

    @Test
    public void testGetBean() {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

        assertTrue(beanDefinition.isSingleton());
        assertFalse(beanDefinition.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, beanDefinition.getScope());

        assertEquals("com.free.winter.version1.PetStoreService", beanDefinition.getBeanClassName());
        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");

        assertNotNull(petStoreService);
        PetStoreService petStoreService1 = (PetStoreService) beanFactory.getBean("petStore");
        assertTrue(petStoreService.equals(petStoreService1));
    }

    @Test
    public void testInvalidBean() {
        BeanFactory beanFactory = new DefaultBeanFactory();

        try {
            beanFactory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            System.out.println("捕获到异常： " + e.getMessage());
            return;
        }

        Assert.fail("except BeanCreationException");
    }

    @Test
    public void testInvalidXML() {
        try {
            Resource resource = new ClassPathResource("xxx.xml");
            xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        } catch (BeanDefinitionStoreException e) {
            System.out.println("捕获到异常： " + e.getMessage());
            return;
        }

        Assert.fail("expect BeanDefinitionStoreException");
    }
}
