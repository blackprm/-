package com.team.shop.controller;

import com.team.shop.annotation.PassToken;
import com.team.shop.bean.TestBean;
import com.team.shop.bean.User;
import com.team.shop.service.TestService;
import com.team.shop.service.TokenService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TokenService tokenService;




    @GetMapping("/user/{id}")
    @PassToken
    public User get(@PathVariable("id") Integer id){
        if(id == 1){
            throw new RuntimeException("无token，请重新登录");
        }
        return testService.select(id);
    }

    @PutMapping("/user")
    public Integer update(Integer id,String name){

        return testService.update(id,name);
    }


    @GetMapping("/getMessage")
    public String getMessage(){
        return "ok";
    }

    @DeleteMapping("/user")
    public Integer delete(Integer id){
        return testService.delete(id);
    }


    @PostMapping("/file")
    public String fild(MultipartFile file, HttpSession session){
        String realPath = "E:\\WorkSpace\\IDEAJAVA\\shop\\src\\main\\resources\\images";
        String originalFilename =System.currentTimeMillis() + file.getOriginalFilename();
        File f = new File(realPath,originalFilename);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  realPath;
    }



    @PostMapping("/user")
    @PassToken
    public Object login(Integer id,String lastName){
        User u = testService.select(id);
        if(u != null && u.getUserName().equals(lastName)){
            String token = tokenService.getToken(u);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","1");
            jsonObject.put("message","登录成功");
            jsonObject.put("token",token);
            return jsonObject;
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","0");
            jsonObject.put("message","密码错误");
            return jsonObject;
        }
    }

}
