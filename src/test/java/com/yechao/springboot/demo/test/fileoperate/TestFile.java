package com.yechao.springboot.demo.test.fileoperate;

import com.alibaba.fastjson.JSON;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestFile {


    @Test
//    @Ignore
    public void testReadFile() {
        String path = "src/test/resources/test.txt";
        File filename = new File(path);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (line != null) {
            try {
                System.out.println(line);
                list.add(line);
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(JSON.toJSONString(list));

    }


    @Test
    public void testWriteFile() throws IOException {
        File filename=new File("src/test/resources/write.txt");
        filename.createNewFile();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename,true)));
        out.write("my name is a\n");
        out.flush();
        out.close();
    }


    @Test
    public void test1() {
        System.out.println(TestFile.class.getResource("/").getFile());

    }
}
