package com.yechao.springboot.demo.test.dataStructure;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yechao111987@126.com
 * @date 2019/1/2 16:10
 */
public class BinTree {
    private BinTree lChild;//左孩子
    private BinTree rChild;//右孩子
    private BinTree root;//根节点
    private Object data; //数据域

    public BinTree(BinTree lChild, BinTree rChild, Object data) {
        super();
        this.lChild = lChild;
        this.rChild = rChild;
        this.data = data;
    }

    public BinTree(Object data) {
        this(null, null, data);
    }

    public BinTree() {
        super();
    }


    //先序遍历
    public void preorder(BinTree root) {
        if (root != null) {
            visit(root.getData());
            preorder(root.lChild);
            preorder(root.rChild);
        }

    }

    //中序遍历
    public void inorder(BinTree root) {
        if (root != null) {
            inorder(root.lChild);
            visit(root.getData());
            inorder(root.rChild);
        }

    }

    //后序遍历
    public void afterorder(BinTree root) {
        if (root != null) {
            afterorder(root.lChild);
            afterorder(root.rChild);
            visit(root.getData());
        }
    }


    public BinTree getRootBinTree(BinTree binTree) {
        if (binTree.getlChild() != null && binTree.getrChild() == null) {
            System.out.println("root tree is " + binTree.getData());
            return binTree;
        } else {
            BinTree tree;
            if ((tree = getRootBinTree(binTree.lChild)) == null)
                return tree;
            else
                return getRootBinTree(binTree.rChild);
        }
    }

    private void visit(Object obj) {
        System.out.print(obj + " ");
    }

    public Object getData() {
        return data;
    }

    public BinTree getRoot() {
        return root;
    }

    public BinTree getlChild() {
        return lChild;
    }

    public BinTree getrChild() {
        return rChild;
    }

    public void setlChild(BinTree lChild) {
        this.lChild = lChild;
    }

    public void setrChild(BinTree rChild) {
        this.rChild = rChild;
    }

    public void setRoot(BinTree root) {
        this.root = root;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
