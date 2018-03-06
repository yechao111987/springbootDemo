package com.api.service.impl;

import com.api.service.TestSystemService;
import com.reposity.mysql.apitest.TestSystem;
import com.reposity.mysql.apitest.TestSystemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TestSystemServiceImpl implements TestSystemService{

    @Autowired
    private TestSystemDao testSystemDao;

    @Override
    public Page<TestSystem> queryByPage(int page, int size) {
        Pageable pageable=new PageRequest(page,size, Sort.Direction.DESC,"createtime");
        return testSystemDao.findAll(pageable);
    }
}
