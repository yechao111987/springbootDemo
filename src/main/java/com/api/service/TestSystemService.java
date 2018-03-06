package com.api.service;

import com.reposity.mysql.apitest.TestSystem;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface TestSystemService {
    Page<TestSystem> queryByPage(int page,int size);

}
