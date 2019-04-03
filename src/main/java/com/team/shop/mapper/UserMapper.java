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
     *  根据id获取用户
     * @param id 用户id
     * @return
     */

    /**
     *  根据邮箱获取用户
     * @param email 用户邮箱
     * @return 用户
     */
    User getUserByEmail(String email);

    User getUser(Integer id);

    /**
     *  更新用户昵称
     * @param id 被更新的用户id
     * @param newNike 用户新昵称
     * @return 成功返回1,失败返回0
     */
    Integer updateUserNike(@Param("id") Integer id, @Param("newNike") String newNike);




}
