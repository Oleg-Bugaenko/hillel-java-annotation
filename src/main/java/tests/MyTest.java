package tests;

import annotation.AfterSuite;
import annotation.BeforeSuite;
import annotation.Test;

public class MyTest {

    @BeforeSuite
    public void before() {
        System.out.println("Method 'before' is completed");
    }

    @Test(priority = 6)
    public void test1() {
        System.out.println("Method 'test1' is completed");
    }

    @Test(priority = 3)
    public void test2() {
        System.out.println("Method 'test2' is completed");
    }

    @Test(priority = 9)
    public void test3() {
        System.out.println("Method 'test3' is completed");
    }

    @AfterSuite
    public void after() {
        System.out.println("Method 'after' is completed");
    }

}
