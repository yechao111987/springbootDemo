package com.yechao.springboot.demo.test;

import org.junit.Test;

public class TestThis {
    public TestThis(String name) {
        this.name = name;
    }

    String name;
    public void printNmae(String name){
        System.out.println(name);
        System.out.println(this.name);
    }

    public TestThis returnName(){
        printNmae("11");
        return this;
    }


}
