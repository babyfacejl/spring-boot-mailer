package com.project.springbootmailer.utils;

import com.project.springbootmailer.models.MyEmail;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Personalization;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public final class MultipleEmailsConverter {

    public static List<Email> convertEmails(String emails) {
        List<Email> result =  Arrays.asList(StringUtils.split(emails, ","))
                .stream()
                .filter(s -> StringUtils.isNotBlank(s))
                .map(m -> new Email(m))
                .collect(Collectors.toList());
        return result;
    }

    public static Mail convertToSendgridMail(MyEmail email) {

        Personalization personalization = new Personalization();
        MultipleEmailsConverter.convertEmails(email.getTo()).stream().forEach(to -> personalization.addTo(to));
        if (isNotBlank(email.getBcc())) {
            MultipleEmailsConverter.convertEmails(email.getBcc()).stream().forEach(bcc -> personalization.addBcc(bcc));
        }
        if (isNotBlank(email.getCc())) {
            MultipleEmailsConverter.convertEmails(email.getCc()).stream().forEach(cc -> personalization.addCc(cc));
        }

        Mail mail = new Mail();
        mail.setFrom(new Email(email.getFrom()));
        mail.setSubject(email.getSubject());
        mail.addPersonalization(personalization);
        mail.addContent(new Content("text/plain", email.getText()));
        return mail;
    }

}
