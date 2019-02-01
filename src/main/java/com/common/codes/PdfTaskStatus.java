package com.common.codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yechao111987@126.com
 * @date 2018/11/9 11:08
 */
public enum PdfTaskStatus {

    WAITING("未开始", 0), PROGRESSING("进行中", 1), FINUSHED("已完成", 2), FAIL("失败", 3);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private PdfTaskStatus(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (PdfTaskStatus c : PdfTaskStatus.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // 普通方法
    public static int getIndex(String name) {
        for (PdfTaskStatus c : PdfTaskStatus.values()) {
            if (c.getName().equals(name)) {
                return c.index;
            }
        }
        return 999;
    }

    public static List<Map<String, Object>> getUserRoles() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (PdfTaskStatus c : PdfTaskStatus.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", c.name);
            map.put("index", c.index);
            list.add(map);
        }
        return list;
    }


    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
