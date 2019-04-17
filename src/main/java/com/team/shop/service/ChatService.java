package com.team.shop.service;

import com.team.shop.bean.Chat;
import com.team.shop.bean.Room;
import com.team.shop.mapper.ChatMapper;
import com.team.shop.pojo.RoomAndChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ChatService {
    private ChatMapper chatMapper;

    @Autowired
    public void setChatMapper(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }


    public void addRoom(Room m){
        m.setRoomCreateTime(new Date());
        chatMapper.addRoom(m);
    }


    public List<RoomAndChat> getRoomAndChatById(Integer from,Integer to){
        return chatMapper.getRoomAndChatById(from, to);
    }

    public void addChat(Chat chat){
        chat.setSendTime(new Date());
        chatMapper.addChat(chat);
    }

    public void setChatRead(Integer chatId){
        chatMapper.setChatRead(chatId);
    }


    public List<RoomAndChat> getUserRoomsByUserId(Integer userId){
        return chatMapper.getUserRoomsByUserId(userId);
    }

    public RoomAndChat getRoomAndChatByRoomId(Integer rooId){
        return chatMapper.getRoomAndChatByRoomId(rooId);
    }

}
