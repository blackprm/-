package com.team.shop.websocket;



import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/api/testscoket")
@Component
public class TestSocket {
    private Integer id = null;
    private static ConcurrentHashMap<Integer,Session> webSocketMap = new ConcurrentHashMap<>();
    private Session session = null;

    public TestSocket(){
//        System.out.println("socket b被创建 ： " + this);
    }
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message){
        if(id == null){
            WebNode webNode = JSON.parseObject(message, WebNode.class);
            this.id = webNode.id;
            if(webSocketMap.containsKey(webNode.id)){
                System.out.println("您正在聊天中!");
                return ;
            }

            webSocketMap.put(webNode.id,this.session);
            System.out.println(webSocketMap);
        }
    }

    @OnError
    public void onError(Session session,Throwable e){
        e.printStackTrace();
    }

    @OnClose
    public void onClose(){
        webSocketMap.remove(id);
        System.out.println("id = " + id + "退出聊天");
    }
}
