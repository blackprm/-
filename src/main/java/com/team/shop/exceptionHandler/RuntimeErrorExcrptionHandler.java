package com.team.shop.exceptionHandler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 为拦截器中出现的异常定制的异常处理类
 */
@ControllerAdvice
@ResponseBody
public class RuntimeErrorExcrptionHandler {


    @ExceptionHandler(RuntimeException.class)
    public Map<String,Object> handlerRuntimeExcaption(Exception e){
        Map<String,Object> jsonObject = new JSONObject();
        jsonObject.put("code",404);
        jsonObject.put("message",e.getMessage());
        return jsonObject;

    }
}
