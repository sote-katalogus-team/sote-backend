package com.katalogus.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(String email, String code) {
        SimpleMailMessage simpleMailMessage = null;
        try {
            simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(MESSAGE_FROM);
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Validation code");
            simpleMailMessage.setText("Dear New User! \n \n Here is your validation code: " + code + "\n \n Sincerely SOTE");
            javaMailSender.send(simpleMailMessage);

        } catch (Exception e) {
            System.out.println("Email: " + email + ". Error: " + e);
        }
    }

}
