package com.yechao.springboot.demo.test;

public class TestThisClass {
    public static void main(String arg[]) {
        TestThis testThis=new TestThis("yechao");
        testThis.printNmae("123");

        TestThis tt;
        tt=testThis.returnName();
        System.out.println(tt.name);
    }
}
