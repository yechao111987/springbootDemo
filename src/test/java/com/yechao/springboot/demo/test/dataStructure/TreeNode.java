package com.yechao.springboot.demo.test.dataStructure;

/**
 * 二叉树节点
 *
 * @Author yechao111987@126.com
 * @date 2019/1/2 15:33
 */
public class TreeNode {

    private String data = null;// 数据部分
    private TreeNode left;// 左节点的引用
    private TreeNode right;// 右节点的引用

    public TreeNode() {
    }

    public TreeNode(String data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
