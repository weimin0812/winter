package com.free.winter.context.support;

import com.free.winter.core.io.ClassPathResource;
import com.free.winter.core.io.Resource;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String configFile) {
        return new ClassPathResource(configFile, this.getBeanClassLoader());
    }
}
