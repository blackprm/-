package com.team.shop.mapper;

import com.team.shop.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     *  添加用户
     * @param u 要添加的用户
     * @return 添加成功返回1,添加失败返回0
     */
    Integer addUser(User u);

    /**
     *  根据邮箱获取用户
     * @param email 用户邮箱
     * @return 用户
     */
    User getUserByEmail(String email);

    /**
     *  邮箱和密码登录
     * @param email 用户邮箱
     * @param password 用户密码
     * @return
     */
    User getUserByEamilAndPassword(@Param("email") String email,@Param("password") String password);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUser(Integer id);

    /**
     *  更新用户昵称
     * @param id 被更新的用户id
     * @param newNike 用户新昵称
     * @return 成功返回1,失败返回0
     */
    Integer updateUserNike(@Param("id") Integer id, @Param("newNike") String newNike);


    /**
     *  根究邮箱更爱密码
     * @param email 用户邮箱
     * @param password 用户新密码
     * @return 更改成功 返回 1,更改失败返回0
     */
    Integer updateUserPasswordByEmail(@Param("email") String email,@Param("password") String password);




}
