package com.api;

import com.api.service.TestSystemService;
import com.pojo.ro.PageRo;
import com.pojo.ro.Response;
import com.reposity.mysql.apitest.TestSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestSystemController {
    @Autowired
    private TestSystemService testSystemService;

    @RequestMapping("/testsystem/list")
    @ResponseBody
    public Response<List<TestSystem>> getTestSystemList(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Response<List<TestSystem>> response = new Response<>();
        if (null == testSystemService.queryByPage(page, size)) {
            response.setCode("1");
            response.setMessage("查询失败");
            System.out.println("111");
            return response;
        }
        System.out.println("222");
        response.setCode("0");
        response.setMessage("查询成功");
        List<TestSystem> list = new ArrayList<>();
        Page<TestSystem> pageinfo = testSystemService.queryByPage(page, size);
        for (TestSystem testSystem : pageinfo.getContent()) {
            list.add(testSystem);
        }
        response.setDataResult(list);
        return response;
    }
}
