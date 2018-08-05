package com.free.winter;

import com.free.winter.version1.test.AllTestsVersion1;
import com.free.winter.version2.test.AllTestsVersion2;
import com.free.winter.version3.test.AllTestsVersion3;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author weimin02
 * @date 2018/8/2
 * @project winter
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllTestsVersion1.class,
        AllTestsVersion2.class,
        AllTestsVersion3.class
})
public class AllTests {
}
