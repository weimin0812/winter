package com.free.winter.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author weimin02
 * @date 2018/8/14
 * @project winter
 */
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    /**
     * Declares whether the annotated dependency is required
     * @return
     */
    boolean required() default true;

}
