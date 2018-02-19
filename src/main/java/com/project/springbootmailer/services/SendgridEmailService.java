package com.project.springbootmailer.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.project.springbootmailer.models.MyEmail;
import com.project.springbootmailer.models.SendResponse;
import com.project.springbootmailer.utils.MultipleEmailsHelper;
import com.project.springbootmailer.utils.SendStatus;
import com.sendgrid.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("backup")
public class SendgridEmailService implements EmailService {
    final static Logger logger = Logger.getLogger(SendgridEmailService.class);

    @Override
    public SendResponse sendEmail(MyEmail email) throws Exception {
        final Mail mail = MultipleEmailsHelper.convertToSendgridMail(email);

        final HttpRequestWithBody body = Unirest.post("https://api.sendgrid.com/v3/mail/send")
                .header("Authorization", "Bearer " +System.getenv("SENDGRID_API_KEY"))
                .header("Content-Type", "application/json");
        if (!StringUtils.isBlank(email.getCc())) {
            body.queryString("cc", email.getCc());
        }
        if (!StringUtils.isBlank(email.getBcc())) {
            body.queryString("bcc", email.getBcc());
        }
        body.body(mail.build());
        HttpResponse<JsonNode> response = body.asJson();
        logger.debug("Mail res status code="+ response.getStatus() + " " + response.getBody());
        return new SendResponse(response.getStatus(), response.getBody().toString(), SendStatus.of(response.getStatus()));

    }
}
