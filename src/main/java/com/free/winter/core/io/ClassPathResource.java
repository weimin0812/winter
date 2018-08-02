package com.free.winter.core.io;

import com.free.winter.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.classLoader.getResourceAsStream(this.path);
        if (inputStream == null){
            throw new FileNotFoundException(path + " can't be open");
        }

        return inputStream;
    }

    @Override
    public String getDescription() {
        return path;
    }
}
