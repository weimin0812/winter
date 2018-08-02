package com.free.winter.util;

/**
 * @author weimin02
 * @date 2018/7/31
 * @project winter
 */
public class Assert {
    public static void notNull(Object object, String message) {
        if (object == null){
            throw new IllegalArgumentException(message);
        }
    }
}
