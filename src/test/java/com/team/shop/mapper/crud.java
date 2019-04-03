package com.team.shop.mapper;

import com.team.shop.bean.t_User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class crud {
    @Autowired
    TestMapper testMapper;


    @Test
    public void add(){
        testMapper.addTestBean("邓子安");
    }

    @Test
    public void update(){
        Integer column = testMapper.updateTestBeanById(5, "邓子安改变");
    }

    @Test
    public void get(){
        t_User testBeanById = testMapper.getTestBeanById(5);
        System.out.println(testBeanById);
    }

    @Test
    public void delete(){
        Integer integer = testMapper.deleteTestBeanById(5);
        System.out.println(integer);
    }

}
