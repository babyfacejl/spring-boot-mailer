package com.project.springbootmailer.models;

import com.project.springbootmailer.utils.SendStatus;
import java.util.Date;

public class MyEmail {
    private String subject;
    private Date created = new Date();
    private Date sent;
    private String to;
    private String cc;
    private String bcc;
    private String text;
    private String from;

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
}
