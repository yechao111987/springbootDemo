package com.api.service;

import com.pojo.ro.Response;
import com.pojo.vo.TestSystemForm;
import com.reposity.mysql.apitest.TestSystem;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

public interface TestSystemService {
    Page<TestSystem> queryByPage(int page,int size);

    Response<TestSystem> save(TestSystemForm testSystemForm);

}
