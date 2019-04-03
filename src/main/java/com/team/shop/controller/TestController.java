package com.team.shop.controller;

import com.team.shop.annotation.PassToken;
import com.team.shop.bean.t_User;
import com.team.shop.service.TestService;
import com.team.shop.service.TokenService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Component
@RequestMapping("/api")
@PropertySource("uploadFiles.properties")
@ConfigurationProperties(prefix = "uploadfiles")

public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private TokenService tokenService;


    @Getter
    @Setter
    public String realPath;


    @GetMapping("/user/{id}")
    @PassToken
    public t_User get(@PathVariable("id") Integer id){
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
    @PassToken
    public String file(MultipartFile []files, HttpSession session){
        System.out.println(realPath);
        System.out.println("file被访问" + files);
        if(files == null){
            return "error:404";
        }
        try {
            for(MultipartFile file:files){

                file.transferTo(new File(realPath + file.getOriginalFilename()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  realPath;
    }





}
