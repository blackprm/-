package com.team.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 向用户发送激活邮件
     * @param toEmail
     * @return
     */
    public Integer sendActiveCode(String toEmail){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1144569608@qq.com");
        message.setTo(toEmail);
        message.setSubject("主题：激活验证码");
        long l = System.currentTimeMillis() % 10000;
        message.setText("农大二手交易平台注册验证码为:" + l);
        mailSender.send(message);
        return (int)l;

    }
}
