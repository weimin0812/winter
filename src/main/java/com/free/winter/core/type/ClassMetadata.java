package com.free.winter.core.type;

/**
 * @author weimin02
 * @date 2018/8/26
 * @project winter
 */
public interface ClassMetadata {
    String getClassName();

    boolean isInterface();

    boolean isAbstract();

    boolean isFinal();

    boolean hasSuperClass();

    String getSuperClassName();

    String[] getInterfaceNames();
}
