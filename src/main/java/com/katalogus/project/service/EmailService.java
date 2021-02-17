package com.katalogus.project.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class EmailService {


    @Value("${spring.sendgrid.api-key}")
    private String APIKEY;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendMessage(String email, String code) {
        System.out.println(fromEmail);
        Email from = new Email(fromEmail);
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
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getHeaders());
            System.out.println(response.getBody());
        } catch (Exception e) {
            System.out.println("Email: " + email + ". Error: " + e);
        }
    }

}
