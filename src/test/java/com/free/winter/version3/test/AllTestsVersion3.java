package com.free.winter.version3.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author weimin02
 * @date 2018/8/5
 * @project winter
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestVersion3.class,
        BeanDefinitionTestVersion3.class,
        ConstructorResolverTest.class
})
public class AllTestsVersion3 {
}
