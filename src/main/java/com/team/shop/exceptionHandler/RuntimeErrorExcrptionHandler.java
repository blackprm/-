package com.team.shop.exceptionHandler;

import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class RuntimeErrorExcrptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Object handlerRuntimeExcaption(Exception e){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",404);
        jsonObject.put("message",e.getMessage());
        return jsonObject;

    }
}
