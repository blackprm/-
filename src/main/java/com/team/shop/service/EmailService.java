package com.team.shop.service;

import com.team.shop.exception.EmailErrorException;
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
    public Integer sendActiveCode(String toEmail) throws EmailErrorException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1144569608@qq.com");
        message.setTo(toEmail);
        message.setSubject("主题：激活验证码");
        long l = System.currentTimeMillis() % 10000;
        message.setText("农大二手交易平台注册验证码为:" + l);
        try {
            mailSender.send(message);
        }catch (Exception e){
            throw new EmailErrorException("邮箱格式错误或不存在");
        }



        return (int)l;

    }
}
