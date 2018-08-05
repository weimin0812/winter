package com.free.winter.version3.test;

import com.free.winter.context.ApplicationContext;
import com.free.winter.context.support.ClassPathXmlApplicationContext;
import com.free.winter.version3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author weimin02
 * @date 2018/8/3
 * @project winter
 */
public class ApplicationContextTestVersion3 {
    @Test
    public void testGetBeanProperty() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        Assert.assertNotNull(petStoreService.getAccountDAO());
        Assert.assertNotNull(petStoreService.getItemDAO());
        Assert.assertEquals(1, petStoreService.getVersion());

    }
}
