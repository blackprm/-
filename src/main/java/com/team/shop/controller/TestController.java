package com.team.shop.controller;

import com.team.shop.bean.TestBean;
import com.team.shop.bean.User;
import com.team.shop.service.TestService;
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


    @PostMapping("/user")
    public Integer add(String name){
        return testService.add(name);
    }

    @GetMapping("/user/{id}")
    public User get(@PathVariable("id") Integer id){
        return testService.select(id);
    }

    @PutMapping("/user")
    public Integer update(Integer id,String name){
        return testService.update(id,name);
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
}
