package com.team.shop.mapper;


import com.team.shop.bean.TestBean;
import org.apache.ibatis.annotations.Mapper;

public interface TestMapper {
    public TestBean getTestBean();
}
