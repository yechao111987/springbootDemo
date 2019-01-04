package com.yechao.springboot.demo.test.dataStructure;

import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 链表
 *
 * @Author yechao111987@126.com
 * @date 2019/1/2 11:35
 */
public class TestLinkList {

    @Test
    public void testSingleLinkedList() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("list1");
        linkedList.add("list2");
        System.out.println(linkedList);
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "2");
        System.out.println(map.toString());

    }
}
