package com.free.winter.beans.factory;

import com.free.winter.beans.BeansException;

/**
 * @author weimin02
 * @date 2018/7/29
 * @project winter
 */
public class BeanDefinitionStoreException extends BeansException {
    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
