package com.free.winter.beans;

import java.util.List;

/**
 * @author weimin02
 * @date 2018/7/28
 * @project winter
 */
public interface BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    public static final String SCOPE_DEFAULT = "prototype";


    String getBeanClassName();

    boolean isSingleton();

    boolean isPrototype();

    String getScope();
    void setScope(String scope);

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    boolean hasConstructorArguments();

    String getId();
}
