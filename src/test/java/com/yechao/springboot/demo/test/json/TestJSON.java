package com.yechao.springboot.demo.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @Author yechao
 * @date ${date} ${time}
 */
public class TestJSON {

    @Test
    public void test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1", "value1");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("11");
    }
}
