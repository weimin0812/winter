package com.free.winter.version4.test;

import com.free.winter.core.io.Resource;
import com.free.winter.core.io.suuport.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author weimin02
 * @date 2018/8/17
 * @project winter
 */
public class PackageResourceLoaderTest {
    @Test
    public void testGetResources() {
        PackageResourceLoader packageResourceLoader = new PackageResourceLoader();
        Resource[] resources = packageResourceLoader.getResources("com.free.winter.version4.dao");
        Assert.assertEquals(2, resources.length);
    }
}
