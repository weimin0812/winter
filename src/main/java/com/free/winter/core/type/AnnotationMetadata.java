package com.free.winter.core.type;

import com.free.winter.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * @author weimin02
 * @date 2018/8/26
 * @project winter
 */
public interface AnnotationMetadata extends ClassMetadata {
    Set<String> getAnnotationTypes();

    boolean hasAnnotation(String annotationType);

    AnnotationAttributes getAnnotationAttributes(String annotationType);
}
