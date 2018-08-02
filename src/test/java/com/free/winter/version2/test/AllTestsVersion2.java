package com.free.winter.version2.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author weimin02
 * @date 2018/8/2
 * @project winter
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestVersion2.class,
        BeanDefinitionTestVersion2.class,
        BeanDefinitionValueResolverTest.class,
        CustomBooleanEditorTest.class,
        CustomNumberEditorTest.class,
        TypeConverterTest.class
})
public class AllTestsVersion2 {
}
