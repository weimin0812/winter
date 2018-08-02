package com.free.winter.version1.test;

import com.free.winter.core.io.ClassPathResource;
import com.free.winter.core.io.FileSystemResource;
import com.free.winter.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public class ResourceTest {
    @Test
    public void testClassPathResource() throws Exception {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        InputStream inputStream = null;

        try {
            inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

    }

    @Test
    public void testFileSystemResource() throws Exception {
        Resource resource = new FileSystemResource("/Users/weimin/Desktop/spring/petstore-v1.xml");
        InputStream inputStream = null;

        try {
            inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
