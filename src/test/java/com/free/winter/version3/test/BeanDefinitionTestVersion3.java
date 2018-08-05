package com.free.winter.version3.test;

import com.free.winter.beans.BeanDefinition;
import com.free.winter.beans.ConstructorArgument;
import com.free.winter.beans.factory.config.RunTimeBeanReference;
import com.free.winter.beans.factory.config.TypedStringValue;
import com.free.winter.beans.factory.support.DefaultBeanFactory;
import com.free.winter.beans.factory.xml.XmlBeanDefinitionReader;
import com.free.winter.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author weimin02
 * @date 2018/8/3
 * @project winter
 */
public class BeanDefinitionTestVersion3 {
    @Test
    public void testConstructorArgument() {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));

        BeanDefinition beanDefinition = defaultBeanFactory.getBeanDefinition("petStore");
        Assert.assertEquals("com.free.winter.version3.PetStoreService", beanDefinition.getBeanClassName());

        ConstructorArgument constructorArgument = beanDefinition.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> valueHolderList = constructorArgument.getArgumentValues();
        Assert.assertEquals(3, valueHolderList.size());

        RunTimeBeanReference accountDAO = (RunTimeBeanReference) valueHolderList.get(0).getValue();
        Assert.assertEquals("accountDAO", accountDAO.getBeanName());

        RunTimeBeanReference itemDAO = (RunTimeBeanReference) valueHolderList.get(1).getValue();
        Assert.assertEquals("itemDAO", itemDAO.getBeanName());

        TypedStringValue version = (TypedStringValue) valueHolderList.get(2).getValue();
        Assert.assertEquals("1", version.getValue());
    }
}
