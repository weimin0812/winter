package com.free.winter.beans;

/**
 * @author weimin02
 * @date 2018/8/2
 * @project winter
 */
public class TypeMismatchException extends BeansException {
    private transient Object value;

    private Class<?> requiredType;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value :" + value + " to type " + requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
