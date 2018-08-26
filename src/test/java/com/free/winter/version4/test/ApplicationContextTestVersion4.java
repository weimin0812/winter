package com.free.winter.version4.test;

import com.free.winter.context.ApplicationContext;
import com.free.winter.context.support.ClassPathXmlApplicationContext;
import com.free.winter.version4.service.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author weimin02
 * @date 2018/8/17
 * @project winter
 */
public class ApplicationContextTestVersion4 {
    @Test
    public void testGetBeanProperty(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v4.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        Assert.assertNotNull(petStoreService.getAccountDAO());
        Assert.assertNotNull(petStoreService.getItemDAO());

    }
}
