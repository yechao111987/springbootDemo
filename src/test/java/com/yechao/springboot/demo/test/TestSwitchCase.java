package com.yechao.springboot.demo.test;

import org.junit.Test;

public class TestSwitchCase {

    String code = "1";

    //注意switch和if的区别，如果没有berak语句，匹配到case后，之后case的情况都会执行
    @Test
    public void test() {
        int code1 = 3;
        switch (code1) {
            case 1:
                System.out.println("1111");
            case 3:
                System.out.println("3333");
            case 2:
                System.out.println("222");
                break;
            case 4:
                System.out.println("3");
                break;
        }
    }
}
