package com.project.springbootmailer.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.project.springbootmailer.models.MyEmail;
import com.project.springbootmailer.models.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class SendEmailService {
    private EmailService mailgunEmailService;
    private EmailService sendgridEmailService;

    @Autowired
    public SendEmailService(@Qualifier("${mailgun.service.type}") EmailService mailgunEmailService, @Qualifier("${sendgrid.service.type}") EmailService sendgridEmailService) {
        this.mailgunEmailService = mailgunEmailService;
        this.sendgridEmailService = sendgridEmailService;
    }

    @HystrixCommand(fallbackMethod = "sendWithBackup", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public SendResponse send(MyEmail email) throws Exception {
        return mailgunEmailService.sendEmail(email);
    }

    private SendResponse sendWithBackup(MyEmail email) throws Exception {
        return sendgridEmailService.sendEmail(email);
    }
}
