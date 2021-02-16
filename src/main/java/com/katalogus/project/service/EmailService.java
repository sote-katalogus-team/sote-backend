package com.katalogus.project.service;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.sendgrid.*;


@Service
public class EmailService {


    @Value("${spring.sendgrid.api-key}")
    private String APIKEY;

    private SendGrid sendgrid = new SendGrid(APIKEY);

    public void sendMessage(String email, String code) {
        Email from = new Email("ifjeszkis@gmail.com");
        String subject = "Validation code";
        Email to = new Email(email);
        Content content = new Content("text/plain", "Dear New User! \n \n Here is your validation code: " + code + "\n \n Sincerely SOTE");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(APIKEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
        } catch (Exception e) {
            System.out.println("Email: " + email + ". Error: " + e);
        }
    }

}
