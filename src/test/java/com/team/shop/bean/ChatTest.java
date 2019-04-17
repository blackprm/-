package com.team.shop.bean;


import com.alibaba.fastjson.JSON;
import com.team.shop.mapper.ChatMapper;
import com.team.shop.pojo.RoomAndChat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
// 在配置完websocket时,只有加上这个注解才能进行单元测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatTest {

    @Autowired
    ChatMapper chatMapper;

    @Test
    public void addRoom(){
        Room room = new Room();
        room.setFkUser1(47);
        room.setFkUser2(48);
        room.setRoomCreateTime(new Date());
        room.setRoomName("这是房间");
        chatMapper.addRoom(room);
        System.out.println(room.getRoomId());
    }

    @Test
    public void addChat(){
        Chat chat = new Chat();
        chat.setChatContent("这还是聊天内容");
        chat.setFkRoomId(1);
        chat.setFrom(47);
        chat.setTo(48);
        chat.setSendTime(new Date());

        chatMapper.addChat(chat);
        System.out.println(chat.getChatId());
    }

    @Test
    public void getRoomAndChatById(){
        List<RoomAndChat> roomAndChatById = chatMapper.getRoomAndChatById(480,47);
//        for(RoomAndChat r : roomAndChatById){
//            System.out.println(r);
//        }

        System.out.println(roomAndChatById);
    }


    @Test
    public void getUserRoomsByUserId(){
        List<RoomAndChat> userRoomsByUserId = chatMapper.getUserRoomsByUserId(47);
        System.out.println(userRoomsByUserId.size());

    }

    @Test
    public void setChatRead(){
        chatMapper.setChatRead(2);
    }


    @Test
    public void getRoomAndChatByRoomId(){
        RoomAndChat roomAndChatByRoomId = chatMapper.getRoomAndChatByRoomId(23);
        System.out.println(JSON.toJSON(roomAndChatByRoomId));
    }

}
