package com.common.util;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yechao111987@126.com
 * @date 2018/6/29 16:20
 */
public class JdbcUtil {
//    private String url;

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return namedParameterJdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate(String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    public List<Map<String, Object>> queryData(NamedParameterJdbcTemplate namedParameterJdbcTemplate, String table, Map<String, Object> map) {
//        NamedParameterJdbcTemplate namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(url, name, password);
        List<Map<String, Object>> list = null;
        try {
            System.out.println(namedParameterJdbcTemplate.toString());
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("select * from " + table + " where ");
            for (String key : map.keySet()) {
                sqlBuffer.append(key + " =:" + key + " and ");
            }
            sqlBuffer.append("1=1");
            System.out.println(sqlBuffer.toString());
//        Map<String,Object> map=new HashMap<>();
            map.put("id", 1);
            list = namedParameterJdbcTemplate.queryForList(sqlBuffer.toString(), map);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询失败，返回为空");
        }
        return list;

    }

    @Test
    public void test2() {
        String url = "jdbc:mysql://127.0.0.1:3306/api";
        String name = "root";
        String password = "yechao";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(url, name, password);
        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");
        map.put("sysname", "系统1");
        String table = "system";
        List<Map<String, Object>> list = queryData(namedParameterJdbcTemplate, table, map);
        System.out.println(JSON.toJSONString(list));

    }


    @Test
    public void test1() {
        String url = "jdbc:mysql://127.0.0.1:3306/api";
        String name = "root";
        String password = "yechao";
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(url, name, password);
        System.out.println(namedParameterJdbcTemplate.toString());
        String sql = "select * from api.system where id = :id";
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        List<Map<String, Object>> list = namedParameterJdbcTemplate.queryForList(sql, map);
        System.out.println(JSON.toJSONString(list));

    }


}
