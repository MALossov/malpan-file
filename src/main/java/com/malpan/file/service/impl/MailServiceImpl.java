package com.malpan.file.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.malpan.file.service.MailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailServiceImpl implements MailService{
    @Resource
    JavaMailSender javaMailSender;

    @Value("malossov@163.com")
    private String from;

    public void SimpleMailSend(String subject,String text,String to){
        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        javaMailSender.send(simpleMailMessage);
    }
}
