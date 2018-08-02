package com.free.winter.beans;

/**
 * @author weimin02
 * @date 2018/8/2
 * @project winter
 */
public interface TypeConverter {

    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
