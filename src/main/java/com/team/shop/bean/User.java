package com.team.shop.bean;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer userId;             // 用户id
    private String userNike;            // 用户昵称
    private String userEmail;           // 用户邮箱
    private String userPhone;           // 用户电话
    private String userPassword;        // 用户密码
    private String userPhotoPath;       // 用户头像名
    private String userDes;             // 用户个性签名
}
