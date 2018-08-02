package com.free.winter.version1.test;

import com.free.winter.context.ApplicationContext;
import com.free.winter.context.support.ClassPathXmlApplicationContext;
import com.free.winter.context.support.FileSystemXmlApplicationContext;
import com.free.winter.version1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public class ApplicationContextTest {

    @Test
    public void testGetBean() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStoreService);
            }

    @Test
    public void testGetBeanFromFileSystemContext(){
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/Users/weimin/Desktop/spring/petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
