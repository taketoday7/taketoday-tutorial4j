package cn.tuyucheng.taketoday.junit5vstestng;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({Class1UnitTest.class, Class2UnitTest.class})
class SelectClassesSuiteUnitTest {
}