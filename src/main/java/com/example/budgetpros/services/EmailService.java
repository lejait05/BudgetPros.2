package com.example.budgetpros.services;

import com.example.budgetpros.model.User;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.Method;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("emailService")
public class EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String key;

    @Value("${SENDGRID_EMAIL}")
    private String sendgridEmail;

    public void prepareAndSend(User user, String subject) {
        Email from = (new Email(sendgridEmail));
        Email to = (new Email("christopher.aa.merritt@gmail.com"));
        String body = "Congratulations on creating a new BudgetPros account, " + user.getFirst_name() + " " + user.getLast_name() + " If this was not you please contact customer support.";
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(key);
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        } catch(MailException | IOException ex){
            ex.printStackTrace();
        }
    }
}

