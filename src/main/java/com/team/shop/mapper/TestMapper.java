package com.team.shop.mapper;


import org.apache.ibatis.annotations.Param;


public interface TestMapper {

    // 增
    public Integer addTestBean(String name);

    // 删
    public Integer deleteTestBeanById(Integer id);

    // 改

    public Integer updateTestBeanById(@Param("id") Integer id, @Param("name") String name);


}
