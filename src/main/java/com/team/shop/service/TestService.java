package com.team.shop.service;

import com.team.shop.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestService {

    @Autowired
    TestMapper testMapper;

    public Integer add(String name){
        return testMapper.addTestBean(name);
    }

    public Integer delete(Integer id){
        Integer integer = testMapper.deleteTestBeanById(id);
        if(integer == 1)
            throw new RuntimeException("测试");
        return integer;
    }

    public Integer update(Integer id,String name){
        return testMapper.updateTestBeanById(id,name);
    }



}
