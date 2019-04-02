package com.team.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// 在配置完websocket时,只有加上这个注解才能进行单元测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1144569608@qq.com");
        message.setTo("1144569608@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        long syscurm = System.currentTimeMillis();
        mailSender.send(message);
        System.out.println(System.currentTimeMillis() - syscurm);
    }

}