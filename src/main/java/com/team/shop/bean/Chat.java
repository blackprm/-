package com.team.shop.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private Integer chatId;
    private Integer from;
    private Integer to;
    private Integer isRead;
    private Date sendTime;
    private String chatContent;
    private Integer fkRoomId;
}
