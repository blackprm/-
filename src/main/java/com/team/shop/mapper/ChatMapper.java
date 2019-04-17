package com.team.shop.mapper;

import com.team.shop.bean.Chat;
import com.team.shop.bean.Room;
import com.team.shop.pojo.RoomAndChat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMapper {

    void addRoom(Room m);

    void addChat(Chat c);


    List<RoomAndChat> getRoomAndChatById(@Param("user1") Integer user1, @Param("user2") Integer user2);

    void setChatRead(Integer chatId);

    List<RoomAndChat> getUserRoomsByUserId(Integer userId);

    RoomAndChat getRoomAndChatByRoomId(Integer roomId);

}
