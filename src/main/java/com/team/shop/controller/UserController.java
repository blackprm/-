package com.team.shop.controller;


import com.team.shop.annotation.PassToken;
import com.team.shop.bean.User;
import com.team.shop.exception.EmailErrorException;
import com.team.shop.pojo.UserInfo;
import com.team.shop.pojo.UserLogin;
import com.team.shop.service.EmailService;
import com.team.shop.service.TokenService;
import com.team.shop.service.UploadFileService;
import com.team.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private TokenService tokenService;
    private EmailService emailService;



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }



    /**
     * 注册用户
     * @param u 用户的信息
     * @return 注册成功 返回 token 和 user 实体
     */
    @PostMapping(value = "/user")
    @PassToken
    public Map<String,Object> userRegister(@RequestBody User u) {
        Map<String,Object> result = new HashMap<>();

        User userByEmail = userService.getUserByEmail(u.getUserEmail());


        if(userByEmail != null){
            result.put("code",-1);
            result.put("message","该已被邮箱注册!");
            return result;
        }else{

            userService.addUser(u);
            // 注册成功
            if(u.getUserId() != null){
                result.put("code",1);
                result.put("message","注册成功,请返回登录");
                return result;
            }else{
                result.put("code",-1);
                result.put("message","出现异常错误!");
                return result;
            }
        }
    }



    /**
     *  向用户邮箱发送验证码
     * @param m 用户数据
     * @return 和邮箱内容一样的验证码
     */
    @PostMapping("/active_code")
    @PassToken
    public Map<String,Object> getActiveCode(@RequestBody Map<String ,Object> m) throws EmailErrorException {
        String userEmail = (String) m.get("userEmail");
        Integer activeCode = emailService.sendActiveCode(userEmail);
        Map<String,Object> result = new HashMap<>();
        result.put("code",1);
        result.put("active_code",activeCode);
        return result;
    }


    @PostMapping("/login")
    @PassToken
    public Map<String,Object> userLogin(@RequestBody  UserLogin us){



        Map<String,Object> res = new HashMap<>();
        String userEmail = us.getUserEmail();
        String password = us.getPassword();
        User u = userService.getUserByEmailAndPassword(userEmail, password);
        if(u == null){
            u = userService.getUserByPhoneAndPassword(userEmail,password);
        }

        if(u == null){
            // 登录失败
            res.put("code",-1);
            res.put("message","用户名或密码错误");
            return res;
        }else{
            // 登录成功
            // 状态码
            res.put("code",1);
            // 用户实体
            res.put("user",u);
            // token令牌
            res.put("token",tokenService.getToken(u));
            return res;
        }

    }


    /**
     *  开放接口
     *  根据邮箱判断用户是否已经在数据库中注册
     * @param emali 用户邮箱
     * @return
     *  {
     *  // 不存在
     *     "code": -1
     * }
     *
     * {
     *      存在
     *     "code": 1
     * }
     */
    @GetMapping("/user/{email}")
    @PassToken
    public Map<String,Object> judgeUserExistByEmail(@PathVariable("email") String emali){
        System.out.println("123123");
        Map<String,Object> res =new HashMap<>();
        User u = userService.getUserByEmail(emali);
        if(u == null){
            // 用户不存在返回 -1
            res.put("code",-1);
            return res;
        }else{
            // 用户存再返回 1
            res.put("code",1);
            return res;
        }
    }


    /**
     * 修改密码的接口,需要token
     * @param email 用户邮箱
     * @param password 用户新密码
     * @return 成功返回 1 失败返回 -1
     */
    @PutMapping("/user/password")
    public Map<String,Object> updateUserPassByEmail(String email,String password){
        Map<String,Object> res = new HashMap<>();

        Integer ok = userService.updateUserPasswordByEmail(email, password);
        if(ok == 1){
            res.put("code",1);
            res.put("message","修改成功,请重新登录");
            return res;
        }else{
            res.put("code",-1);
            res.put("message","修改失败,请检查信息");
            return res;
        }
    }

    /**
     *
     */
    @PostMapping("/updateUserInfoToUserNikeAndPhone")
    public Map<String,Object> updateUserInfoToUserNikeAndPhone(@RequestBody UserInfo userInfo, HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println(token);



        Map<String,Object> res = new HashMap<>();
        Integer id = tokenService.getUserId(token);
        Integer newNike = userService.updateUserNike(id, userInfo.getName());
        if(userService.updateUserPic(id,userInfo.getPic()) == 1){
            res.put("picMessage","修改昵称成功");
        }
        if(newNike == 1){
            res.put("nikeMessage","修改昵称成功");
        }

        Integer newPhone = userService.updateUserPhone(id, userInfo.getPhone());
        if(newPhone == 1){
            res.put("phoneMessage","修改手机号码成功");
        }
        return res;
    }
}
