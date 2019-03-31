package com.team.shop.controller;

import com.team.shop.bean.TestBean;
import com.team.shop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public TestBean test(){

        return testService.getTestBean();
    }


}
