package com.free.winter.version2.test;

import com.free.winter.context.ApplicationContext;
import com.free.winter.context.support.ClassPathXmlApplicationContext;
import com.free.winter.version2.PetStoreService;
import com.free.winter.version2.dao.AccountDAO;
import com.free.winter.version2.dao.ItemDAO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author weimin02
 * @date 2018/8/1
 * @project winter
 */
public class ApplicationContextTestVersion2 {
    @Test
    public void testGetBeanProperty(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService.getAccountDAO());
        assertNotNull(petStoreService.getItemDAO());

        assertTrue(petStoreService.getAccountDAO() instanceof AccountDAO);
        assertTrue(petStoreService.getItemDAO() instanceof ItemDAO);
        assertEquals("weimin02",petStoreService.getOwner());
        assertEquals(5,petStoreService.getVersion());

    }
}
