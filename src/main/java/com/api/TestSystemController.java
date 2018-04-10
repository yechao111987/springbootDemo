package com.api;

import com.alibaba.fastjson.JSON;
import com.api.service.TestSystemService;
import com.api.service.impl.TestSystemServiceImpl;
import com.pojo.ro.Response;
import com.pojo.vo.TestSystemForm;
import com.reposity.mysql.apitest.TestSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestSystemController {
    @Autowired
    private TestSystemService testSystemService;

    private static Logger logger = LoggerFactory.getLogger(TestSystemController.class);


    @RequestMapping("/testsystem/list")
    @ResponseBody
    public Response<List<TestSystem>> getTestSystemList(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Response<List<TestSystem>> response = new Response<>();
        if (null == testSystemService.queryByPage(page, size)) {
            response.setCode("1");
            response.setMessage("查询失败");
            return response;
        }
        response.setCode("0");
        response.setMessage("查询成功");
        List<TestSystem> list = new ArrayList<>();
        Page<TestSystem> pages = testSystemService.queryByPage(page, size);
        logger.info("查询结果为：" + JSON.toJSONString(pages));
        for (TestSystem testSystem : pages.getContent()) {
            list.add(testSystem);
        }
        response.setDataResult(list);
        return response;
    }

    @PostMapping("/testsystem/add")
    @ResponseBody
    public Response<TestSystem> addtestSystem(@RequestBody TestSystemForm testSystemForm){
        logger.info(testSystemForm.getDescription()+","+testSystemForm.getStatus());
        return testSystemService.save(testSystemForm);
    }


}
