package com.team.shop.controller;

import com.team.shop.annotation.PassToken;
import com.team.shop.bean.User;
import com.team.shop.service.EmailService;
import com.team.shop.service.TokenService;
import com.team.shop.service.UploadFileService;
import com.team.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private TokenService tokenService;
    private EmailService emailService;
    private UploadFileService uploadFileService;

    @Autowired
    public void setUploadFileService(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }
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
    @PostMapping("/user")
    @PassToken
    public Map<String,Object> userRegister(MultipartFile file,User u) throws IOException {
        Map<String,Object> result = new HashMap<>();
        if(file == null){
            result.put("code",-1);
            result.put("message","请选择头像!");
             return result;
        }
        User userByEmail = userService.getUserByEmail(u.getUserEmail());


        if(userByEmail != null){

            result.put("code",-1);
            result.put("message","该已被邮箱注册!");
            return result;
        }else{
            String finalFileName = UUID.randomUUID() + file.getOriginalFilename();
            // 存储文件
            uploadFileService.saveFileAsName(file,finalFileName);

            u.setUserPhotoPath(finalFileName);

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
     * @param userEmail 用户的邮箱
     * @return 和邮箱内容一样的验证码
     */
    @PostMapping("/active_code")
    @PassToken
    public Map<String,Object> getActiveCode(String userEmail){
        Integer activeCode = emailService.sendActiveCode(userEmail);
        Map<String,Object> result = new HashMap<>();
        result.put("code",1);
        result.put("active_code",activeCode);
        return result;
    }


    /**
     *  用户登录 凭借着 邮箱 登录
     * @param userEmail     用户邮箱
     * @param password      用户密码
     * @return
     *     成功返回
     * {
     *     "code": 1,
     *     "user": {
     *         "userId": 21,
     *         "userPhotoPath": "a8487da9-c361-44d3-a03d-7f399cf2e659WIN_20190331_23_35_42_Pro.jpg",
     *         "userNike": "blackprm",
     *         "userEmail": "1144569608@qq.com",
     *         "userPhone": "15579191230",
     *         "userPassword": "123456789"
     *     },
     *     "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMSJ9.wV2DymVJXJg2pAn18wUa0La5ViLLuQvyizfpNT2CdKk"
     * }
     *     失败返回
     *
     */
    @PostMapping("/login")
    @PassToken
    public Map<String,Object> userLogin(String userEmail,String password){
        Map<String,Object> res = new HashMap<>();
        User u = userService.getUserByEmailAndPassword(userEmail, password);

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
}
