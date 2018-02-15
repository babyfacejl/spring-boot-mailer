package com.project.springbootmailer.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.project.springbootmailer.models.MyEmail;
import com.project.springbootmailer.models.SendResponse;
import com.project.springbootmailer.utils.SendStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("${mailgun.email.type}")
public class MailgunEmailService implements EmailService {
    final static Logger logger = Logger.getLogger(MailgunEmailService.class);

    @Override
    public SendResponse sendEmail(MyEmail email) throws Exception {
        final HttpRequestWithBody body = Unirest.post("https://api.mailgun.net/v3/sandbox4201cc9e3ca0415db5a10078da1a3376.mailgun.org/messages")
                .basicAuth("api", System.getenv("MAILGUN_API_KEY"))
                .queryString("from", email.getFrom())
                .queryString("to", email.getTo())
                .queryString("subject", email.getSubject())
                .queryString("text", email.getText());
        if (email.getBcc() != null) {
            body.queryString("cc", email.getCc());
        }
        if (email.getCc() != null) {
            body.queryString("bcc", email.getBcc());
        }

        HttpResponse<JsonNode> response = body.asJson();
        logger.debug("Mail status code="+ response.getStatus());
        return new SendResponse(response.getStatus(), response.getBody().toString(), SendStatus.of(response.getStatus()));

    }
}
