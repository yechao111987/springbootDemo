package com.yechao.springboot.demo.test.testng;

import org.testng.annotations.*;

/**
 * @Author yechao111987@126.com
 * @date 2018/12/28 15:11
 */
public class TestNGTest {

    /**
     * 执行顺序:
     *
     * @BeforeSuite->@BeforeTest->@BeforeClass->{@BeforeMethod->@Test->@AfterMethod}->@AfterClass->@AfterTest->@AfterSuite 其中{}内的与多少个@Test，就循环执行多少次
     **/

    @BeforeSuite
    public void beforeSuit() {
        System.out.println("befor suit");
    }


    @BeforeTest
    public void beforeTest() {
        System.out.println("before test");
    }

    @BeforeGroups
    public void beforeGroups() {
        System.out.println("before groups");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("before method");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test(enabled = true)
    public void test2() {
        System.out.println("test2");
    }

    @Test(threadPoolSize = 2, invocationCount = 4, groups = "group1", timeOut = 1000, suiteName = "yechao",
            dependsOnMethods = {"test2"})
    public void test3() {
        System.out.println("test3,thread:" + Thread.currentThread().getId());
    }
}
