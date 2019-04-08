package com.team.shop.websocket;



import com.alibaba.fastjson.JSON;
import com.team.shop.bean.User;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/api/testscoket")
@Component
public class TestSocket {

    private static ConcurrentHashMap<User,Session> map = new ConcurrentHashMap<>();
    private User u = null;
    @OnOpen
    public void onOpen(Session session){

    }

    @OnMessage
    public void onMessage(String message){
        if(u == null){

        }else{

        }
    }

    @OnError
    public void onError(Session session,Throwable e){
        e.printStackTrace();
    }

    @OnClose
    public void onClose(){

    }
}
