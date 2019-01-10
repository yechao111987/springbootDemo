package com.yechao.springboot.demo.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @Author yechao111987@126.com
 * @date 2019/1/4 11:31
 */
public class TestString {

    private static final Logger logger = LoggerFactory.getLogger(TestString.class);

    @Test
    public void testIndex() {
        //获取指定字符出现的位置 indexOf lastIndexOf
        String name = " My Name Is YeChao! ";
        int a = name.indexOf("e");
        logger.info("index of {} ", name.indexOf("e", 2));
        logger.info("index of {} ", name.indexOf("e", 8));
        logger.info("index of {} ", name.indexOf("e", 14));
        logger.info("index of {} ", a);
        int b = name.lastIndexOf("e");
        logger.info("last index of {} ", b);

        //获取指定索引位置的字符 charAt
        logger.info("char at {} is {}", b, name.charAt(b));
        logger.info("char at {} is {}", 0, name.charAt(0));

        //subString 获取指定位置的字符串
        logger.info("subString(1) is {}", name.substring(1));
        logger.info("subString(1,5) is {}", name.substring(1, 5));

        //trim
        logger.info("trim is {}", name.trim());

        //replace 替换
        logger.info("replace a to b is {}", name.replace('a', 'b'));
        logger.info("replaceFirst a to b is {}", name.replaceFirst("a", "b"));
        logger.info("replaceAll a to b is {}", name.replaceAll("a", "b"));

        //startWith endWith 返回是否以XX字符串为开头或者结尾
        logger.info("name is start with My? {}", name.startsWith("My"));
        logger.info("name is edn with Chao ? {}", name.endsWith("Chao! "));

        //判断字符是否相等
        logger.info("equals is {}", name.equals(" My Name Is YeChao! "));
        logger.info("equalsIgnoreCase is {}", name.equalsIgnoreCase(" my name is yechao! "));

        //字母大小写转换
        logger.info("to low case is {}", name.toLowerCase());
        logger.info("to upper case is {}", name.toUpperCase());

        //字符串分割
        logger.info("spilt {}", name.split("e").length);
        logger.info("spilt {}", name.split("\\S+").length);
        String info = "my name is yechao";
        logger.info("spilt {}", info.split("\\S+").length);
        logger.info("spilt {}", info.split(" "));
        String[] ss = info.split(" ");
        System.out.println(ss.length);
        for (String s : ss) {
            System.out.println(s);
        }


    }

}
