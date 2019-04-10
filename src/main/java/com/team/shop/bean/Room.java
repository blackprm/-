package com.team.shop.bean;


import lombok.*;

import java.util.Date;

@Data
public class Room {

    private Integer roomId;
    private String roomName;
    private Integer fkUser1;
    private Integer fkUser2;
    private Date roomCreateTime;

}
