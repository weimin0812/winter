package com.free.winter.context.support;

import com.free.winter.core.io.FileSystemResource;
import com.free.winter.core.io.Resource;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String configFile) {
        return new FileSystemResource(configFile);
    }
}
