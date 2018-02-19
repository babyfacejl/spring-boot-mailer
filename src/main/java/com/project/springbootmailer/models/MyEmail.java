package com.project.springbootmailer.models;

import com.project.springbootmailer.utils.MultipleEmailsHelper;
import com.project.springbootmailer.utils.SendStatus;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.project.springbootmailer.utils.MultipleEmailsHelper.validateEmails;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class MyEmail {
    private String subject;
    private Date created = new Date();
    private Date sent;
    private String to;
    private String cc;
    private String bcc;
    private String text;
    private String from;
    private List<String> errorMsgs = new ArrayList<>();

    public MyEmail() {
    }

    public MyEmail(String from, String to, String cc, String bcc, String text, String subject) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.text = text;
        this.subject = subject;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "MyEmail{" +
                "subject='" + subject + '\'' +
                ", created=" + created +
                ", sent=" + sent +
                ", to='" + to + '\'' +
                ", cc='" + cc + '\'' +
                ", bcc='" + bcc + '\'' +
                ", text='" + text + '\'' +
                ", from='" + from + '\'' +
                '}';
    }

    public List<String> validate() {
        errorMsgs.clear();

        if (isBlank(this.from)) {
            errorMsgs.add("From is required.");
        } else if (!validateEmails(this.from)){
            errorMsgs.add("From contains invalid email address.");
        }
        if (isBlank(this.to)) {
            errorMsgs.add("To is required.");
        } else if (!validateEmails(this.to)){
            errorMsgs.add("To contains invalid email address.");
        }
        if (isNotBlank(this.cc) && !validateEmails(this.cc)) {
            errorMsgs.add("Cc contains invalid email address.");
        }
        if (isNotBlank(this.bcc) && !validateEmails(this.bcc)) {
            errorMsgs.add("Bcc contains invalid email address.");
        }
        if (isBlank(this.subject)) {
            errorMsgs.add("Subject is required.");
        }
        if (isBlank(this.text)) {
            errorMsgs.add("Body is required.");
        }

        return errorMsgs;
    }
}
