package com.team.shop.service;

import com.team.shop.bean.TestBean;
import com.team.shop.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public TestBean getTestBean(){
        return testMapper.getTestBean();
    }
}
