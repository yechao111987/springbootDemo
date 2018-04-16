package com.api.service.impl;

import com.api.TestSystemController;
import com.api.service.TestSystemService;
import com.pojo.ro.Response;
import com.pojo.vo.TestSystemForm;
import com.reposity.mysql.apitest.TestSystem;
import com.reposity.mysql.apitest.TestSystemDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TestSystemServiceImpl implements TestSystemService{

    @Autowired
    private TestSystemDao testSystemDao;

    private static Logger logger = LoggerFactory.getLogger(TestSystemServiceImpl.class);


    @Override
    public Page<TestSystem> queryByPage(int page, int size) {
        Pageable pageable=new PageRequest(page,size, Sort.Direction.DESC,"createtime");
        return testSystemDao.findAll(pageable);
    }

    @Override
    public Response<TestSystem> save(TestSystemForm testSystemForm) {
        Response<TestSystem> response=new Response<>();
        TestSystem testSystem=new TestSystem();
        testSystem.setStatus("1");
        testSystem.setSysname(testSystemForm.getSysname());
        testSystem.setDescription(testSystemForm.getDescription());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义要输出日期字符串的格式
        Date date=new Date();
        String timeString=sdf.format(date);
        testSystem.setCreatetime(timeString);
        testSystem.setUpdatetime(timeString);
        logger.error(timeString);
        logger.warn(testSystem.getUpdatetime()+","+testSystem.getDescription());
        if(null != testSystemDao.save(testSystem)){
            response.setCode("0");
            response.setMessage("新增数据成功");
            logger.info(testSystem.getUpdatetime());
            return response;
//            logger.info(testSystem.getCreatetime());
        }
//        logger.info(testSystem.getUpdatetime());

        response.setCode("1");
        response.setCode("新增失败");
        response.setDataResult(testSystem);
        return response;
    }


    @Test
    public void test(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义要输出日期字符串的格式
        Date date=new Date();
        String timeString=sdf.format(date);
        System.out.println(timeString);
        TestSystem testSystem=new TestSystem();
        testSystem.setUpdatetime(timeString);
        System.out.println(testSystem.getUpdatetime());



    }
}
