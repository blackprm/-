package com.team.shop.pojo;

import com.team.shop.bean.Chat;
import com.team.shop.bean.Room;
import com.team.shop.bean.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class RoomAndChat extends Room {


    private User user1;
    private User user2;


    private List<Chat>  chats;


    public void setRoom(Room m){
        super.setRoomId(m.getRoomId());
        super.setRoomName(m.getRoomName());
        super.setFkUser1(m.getFkUser1());
        super.setFkUser2(m.getFkUser2());
        super.setRoomCreateTime(m.getRoomCreateTime());
    }
}
