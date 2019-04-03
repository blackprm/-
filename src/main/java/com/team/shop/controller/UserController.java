package com.team.shop.controller;

import com.team.shop.annotation.PassToken;
import com.team.shop.bean.User;
import com.team.shop.service.EmailService;
import com.team.shop.service.TokenService;
import com.team.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/user")
    @PassToken
    public Map<String,Object> userRegister(User u){
        User userByEmail = userService.getUserByEmail(u.getUserEmail());
        if(userByEmail != null){
            Map<String,Object> result = new HashMap<>();
            result.put("code",-1);
            result.put("message","该已被邮箱注册!");
            return result;
        }else{
            Integer integer = userService.addUser(u);
            // 注册成功
            if(u.getUserId() != null){
                Map<String,Object> result = new HashMap<>();
                result.put("code",1);
                result.put("user",u);
                String token = tokenService.getToken(u);
                result.put("token",token);
                return result;
            }else{
                Map<String,Object> result = new HashMap<>();
                result.put("code",-1);
                result.put("message","出现异常错误!");
                return result;
            }
        }
    }

    @PostMapping("/active_code")
    @PassToken
    public Map<String,Object> getActiveCode(String userEmail){
        Integer activeCode = emailService.sendActiveCode(userEmail);

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code",1);
        result.put("active_code",activeCode);

        return result;
    }
}
