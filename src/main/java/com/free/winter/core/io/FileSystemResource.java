package com.free.winter.core.io;


import com.free.winter.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public class FileSystemResource implements Resource {
    private final String path;
    private final File file;


    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file: " + this.file.getAbsolutePath();
    }
}
