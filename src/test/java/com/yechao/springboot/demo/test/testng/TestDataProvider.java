package com.yechao.springboot.demo.test.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataProvider {

    @DataProvider(name = "data",parallel = true)
    public static Object[][] data() {
        return new Object[][]{
                {"http://nos.netease.com/ysf/34048B03F3009A2E7B88AC1FA9DC2CC0", "12", "200"},
                {"http://nos.netease.com/ysf/2D52046108C3A14C916044DB0EAE3690", "12", "200"},
                {"http://nos.netease.com/ysf/E17B35547E0FB2D2CF7FF36D729C680F", "12", "200"},
                {"http://nos.netease.com/ysf/32C4FFC6461BC81415FD510BDCCA88CD", "12", "200"},
                {"http://nos.netease.com/ysf/EA112EC08A57A2AA208556031ED5C28C", "12", "200"},
                {"http://nos.netease.com/ysf/CC174308B60E2A09190AC9C8F584F66A", "12", "200"},
                {"http://nos.netease.com/ysf/0E62255729E64DBD5CFBDE389F5CD413", "12", "200"},
                {"http://nos.netease.com/ysf/F0EDB65A3A25620225AD3CD215B89334", "12", "200"},
                {"http://nos.netease.com/ysf/CDBFD5866ECFFA3AAB8B106341E0A69A", "12", "200"},
                {"http://nos.netease.com/ysf/C7794FDBDAFBDC0EB053F24FDF513E3D", "12", "200"},
        };
    }


    @Test(dataProvider = "data")
    public void test(String url, String token, String res) throws InterruptedException {
        long time = System.currentTimeMillis();
        System.out.println("time:" + time);
        Thread.currentThread().sleep(5000);
        System.out.println("url:" + url + ", token:" + token + ", res:" + res);
        Thread.currentThread().sleep(500);


    }
}
