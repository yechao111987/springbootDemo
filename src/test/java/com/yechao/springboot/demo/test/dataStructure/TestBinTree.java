package com.yechao.springboot.demo.test.dataStructure;

import org.junit.Test;

/**
 * @Author yechao111987@126.com
 * @date 2019/1/2 16:29
 */
public class TestBinTree {

    @Test
    public void test1() {
        BinTree binTreeRoot = new BinTree();
        binTreeRoot.setData("root");
        BinTree binTree_left_1 = new BinTree();
        binTree_left_1.setData("left 1");
        BinTree binTree_right_1 = new BinTree();
        binTree_right_1.setData("right 1");
        BinTree binTree_right_11 = new BinTree();
        binTree_right_11.setData("right 11");
        BinTree binTree_right_12 = new BinTree();
        binTree_right_12.setData("right 12");
        BinTree binTree_left_11 = new BinTree();
        binTree_left_11.setData("left 11");
        BinTree binTree_left_12 = new BinTree();
        binTree_left_12.setData("left 12");
        binTreeRoot.setlChild(binTree_left_1);
        binTree_left_1.setlChild(binTree_left_11);
        binTree_left_1.setrChild(binTree_left_12);
        binTreeRoot.inorder(binTreeRoot);
        binTreeRoot.getRootBinTree(binTree_left_11);
    }

}
