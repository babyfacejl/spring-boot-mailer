package com.project.springbootmailer.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.project.springbootmailer.models.MyEmail;
import com.project.springbootmailer.models.SendResponse;
import com.project.springbootmailer.utils.SendStatus;
import com.sendgrid.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Qualifier("${sendgrid.email.type}")
public class SendgridEmailService implements EmailService {
    final static Logger logger = Logger.getLogger(SendgridEmailService.class);

    @Override
    public SendResponse sendEmail(MyEmail email) throws Exception {
        Email from = new Email(email.getFrom());
        String subject = email.getSubject();
        Email to = new Email(email.getTo());
        Content content = new Content("text/plain", email.getText());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            logger.debug("Mail status code="+ response.getStatusCode());

            return new SendResponse(response.getStatusCode(), response.getBody(), SendStatus.of(response.getStatusCode()));
        } catch (IOException ex) {
            return new SendResponse(400, ex.getMessage(), SendStatus.ERROR);
        }

    }

}
