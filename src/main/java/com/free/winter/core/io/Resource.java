package com.free.winter.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
    String getDescription();
}
