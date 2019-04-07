package com.team.shop.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class UserInfo {

    private String name;
    private String phone;
    private String pic;

}
