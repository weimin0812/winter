package com.free.winter.stereotype;

import java.lang.annotation.*;

/**
 * @author weimin02
 * @date 2018/8/14
 * @project winter
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    /**
     *The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component
     * @return the suggested component name, if any
     */
    String value() default "";

}
