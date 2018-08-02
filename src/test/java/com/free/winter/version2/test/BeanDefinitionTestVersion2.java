package com.free.winter.version2.test;

import com.free.winter.beans.BeanDefinition;
import com.free.winter.beans.PropertyValue;
import com.free.winter.beans.factory.config.RunTimeBeanReference;
import com.free.winter.beans.factory.support.DefaultBeanFactory;
import com.free.winter.beans.factory.xml.XmlBeanDefinitionReader;
import com.free.winter.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author weimin02
 * @date 2018/8/1
 * @project winter
 */
public class BeanDefinitionTestVersion2 {
    @Test
    public void testGetBeanDefinition(){
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));

        BeanDefinition beanDefinition = defaultBeanFactory.getBeanDefinition("petStore");

        List<PropertyValue> propertyValueList = beanDefinition.getPropertyValues();

        Assert.assertTrue(propertyValueList.size() == 4);

        {
            PropertyValue propertyValue = getPropertyValue("accountDAO", propertyValueList);
            Assert.assertNotNull(propertyValue);
            Assert.assertTrue(propertyValue.getValue() instanceof RunTimeBeanReference);
        }

        {
            PropertyValue propertyValue = getPropertyValue("itemDAO", propertyValueList);
            Assert.assertNotNull(propertyValue);
            Assert.assertTrue(propertyValue.getValue() instanceof RunTimeBeanReference);
        }

    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> propertyValueList) {
        for (PropertyValue propertyValue : propertyValueList){
            if (propertyValue.getName().equals(name)){
                return propertyValue;
            }
        }

        return null;

    }

}
