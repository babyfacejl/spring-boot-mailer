package com.project.springbootmailer.utils;

import com.project.springbootmailer.models.MyEmail;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Personalization;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public final class MultipleEmailsHelper {
    private static final String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static List<Email> convertEmails(String emails) {
        final List<Email> result =  Arrays.asList(StringUtils.split(emails, ","))
                .stream()
                .filter(s -> StringUtils.isNotBlank(s))
                .map(m -> new Email(m.trim()))
                .collect(Collectors.toList());
        return result;
    }

    public static boolean validateEmails(String emails) {
        Pattern pattern = Pattern.compile(regex);
        final List<Email> results = convertEmails(emails);
        for (final Email email: results) {
            if (!pattern.matcher(email.getEmail()).matches()) {
                return false;
            }
        }
        return true;
    }

    public static Mail convertToSendgridMail(MyEmail email) {

        Personalization personalization = new Personalization();
        MultipleEmailsHelper.convertEmails(email.getTo()).stream().forEach(to -> personalization.addTo(to));
        if (isNotBlank(email.getBcc())) {
            MultipleEmailsHelper.convertEmails(email.getBcc()).stream().forEach(bcc -> personalization.addBcc(bcc));
        }
        if (isNotBlank(email.getCc())) {
            MultipleEmailsHelper.convertEmails(email.getCc()).stream().forEach(cc -> personalization.addCc(cc));
        }

        Mail mail = new Mail();
        mail.setFrom(new Email(email.getFrom()));
        mail.setSubject(email.getSubject());
        mail.addPersonalization(personalization);
        mail.addContent(new Content("text/plain", email.getText()));
        return mail;
    }

}
