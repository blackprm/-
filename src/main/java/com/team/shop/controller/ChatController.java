package com.team.shop.controller;


import com.team.shop.annotation.PassToken;
import com.team.shop.bean.Chat;
import com.team.shop.bean.Room;
import com.team.shop.bean.User;
import com.team.shop.pojo.RoomAndChat;
import com.team.shop.service.ChatService;
import com.team.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    private ChatService chatService;
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }


    @PostMapping("/chat")
    public Object addChat(@RequestBody Chat chat){
        chatService.addChat(chat);
        chat.setIsRead(0);
        return chat;
    }

    @PostMapping("/room")
    @PassToken
  public Map<String,Object> addRoom(@RequestBody Room room){

        Map<String ,Object> res = new HashMap<>();
        List<RoomAndChat> roomAndChatById = chatService.getRoomAndChatById(room.getFkUser1(), room.getFkUser2());
        if(roomAndChatById.size() == 0){
            RoomAndChat roomAndChat = new RoomAndChat();
            User user1 = userService.getUser(room.getFkUser1());
            User user2 = userService.getUser(room.getFkUser2());

            chatService.addRoom(room);


            roomAndChat.setRoom(room);

            roomAndChat.setUser1(user1);
            roomAndChat.setUser2(user2);
            roomAndChat.setChats(new ArrayList<>());

            res.put("room",roomAndChat);
            return res;
        }else{
            res.put("room",roomAndChatById.get(0));
            return res;
        }
    }


    @GetMapping("/room/{id1}/{id2}")
    public Map<String,Object> getUser1AndUser2Room(
            @PathVariable("id1") Integer id1,
            @PathVariable("id2") Integer id2){

        List<RoomAndChat> roomAndChatById = chatService.getRoomAndChatById(id1, id2);
        HashMap<String, Object> res = new HashMap<>();
        if(roomAndChatById.size() == 0){
            res.put("code",-1);
            res.put("room",null);
            return res;
        }else{
            res.put("code",1);
            res.put("room",roomAndChatById.get(0));
            return res;
        }
    }


    @GetMapping("/room/{id}")
    public Map<String,Object> getUserRoomByUserId(@PathVariable("id") Integer id){
        List<RoomAndChat> userRoomsByUserId = chatService.getUserRoomsByUserId(id);

        HashMap<String, Object> res = new HashMap<>();

        if(userRoomsByUserId.size() == 0){
            res.put("code",-1);
            return res;
        }else{
            res.put("code",1);
            res.put("rooms",userRoomsByUserId);
            return res;
        }

    }

    @PostMapping("/chat/read/{id}")
    @PassToken
    public void setChatRead(@PathVariable("id") Integer id){
        chatService.setChatRead(id);
    }


    @GetMapping("/room/roomid/{roomid}")
    @PassToken
    public Map<String,Object> getRoomAndChatByRoomId(@PathVariable("roomid") Integer id){
        Map<String,Object> res =  new HashMap<>();
        RoomAndChat roomAndChatByRoomId = chatService.getRoomAndChatByRoomId(id);
        if(roomAndChatByRoomId != null){
            res.put("code",1);
            res.put("room",roomAndChatByRoomId);
        }else {
            res.put("code",-1);
            res.put("message","未找到指定聊天室");
        }
        return res;
    }

}
