package com.project.springbootmailer.models;

import com.project.springbootmailer.utils.SendStatus;

public class SendResponse {
    private int statusCode;
    private String body;
    private SendStatus sendStatus;

    public SendResponse(int statusCode, String body, SendStatus sentStatus) {
        this.statusCode = statusCode;
        this.body = body;
        this.sendStatus = sentStatus;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public SendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }
}
