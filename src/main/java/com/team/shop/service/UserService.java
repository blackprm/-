package com.team.shop.service;

import com.team.shop.bean.User;
import com.team.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    /**
     *  根据id获取用户
     * @param id 用户id
     * @return
     */
    public User getUser(Integer id){
        return userMapper.getUser(id);
    }

    /**
     *  添加用户
     * @param u 要添加的用户
     * @return 添加成功返回1,添加失败返回0
     */
    public Integer addUser(User u){
        return userMapper.addUser(u);
    }

    /**
     *  根据邮箱获取用户
     * @param email 用户邮箱
     * @return 用户
     */
    public User getUserByEmail(String email){
        return userMapper.getUserByEmail(email);
    }
}
