package com.team.shop.bean;

import com.team.shop.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// 在配置完websocket时,只有加上这个注解才能进行单元测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testUeseExist(){
        User u = new User();
        System.out.println(u);
    }

    @Test
    /**
     *  增加用户
     */
    public void addUser(){
        User user = new User();
        user.setUserNike("blackprm");
        user.setUserEmail("1144569608@qq.com");
        user.setUserPhone("15579191230");
        user.setUserPhotoPath("defaultPath");
        user.setUserPassword("1245645456");
        userMapper.addUser(user);
        System.out.println(user);
    }

    @Test
    /**
     * 获取用户
     */
    public void getUser(){
        User i_like = userMapper.getUser(7);
        System.out.println(i_like);
    }

    @Test
    /**
     * 获取用户
     */
    public void getUserByEmail(){
        User i_like = userMapper.getUserByEmail("1144569608@qq.com");
        System.out.println(i_like);
    }



    @Test
    /**
     * 更改用户昵称
     */
    public void updateUserNike(){
        Integer i_like = userMapper.updateUserNike(6, "I like");
        System.out.println(i_like);
    }


    @Test
    /**
     * 更改用户昵称
     */
    public void getUserByEmailAndPassword(){
        User u = userMapper.getUserByEamilAndPassword("1144569608@qq.com", "123456789");
        System.out.println(u);
    }



    @Test
    /**
     * 更改用户昵称
     */
    public void updateUserPasswordByEmail(){
        Integer i = userMapper.updateUserPasswordByEmail("1144569608@qq.com", "xxxxx");
        System.out.println(i);
    }
}
