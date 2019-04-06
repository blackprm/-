package com.team.shop.exceptionHandler;


import com.alibaba.fastjson.JSONObject;
import com.team.shop.exception.EmailErrorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
@ResponseBody
public class EmailErrorExceptionHandler {

    @ExceptionHandler(EmailErrorException.class)
    public Map<String,Object> emailErrorException(Exception e){
        Map<String,Object> jsonObject = new JSONObject();
        jsonObject.put("code",-1);
        jsonObject.put("message",e.getMessage());
        return jsonObject;
    }

}
