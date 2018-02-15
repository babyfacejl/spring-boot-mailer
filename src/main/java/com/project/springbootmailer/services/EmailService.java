package com.project.springbootmailer.services;

import com.project.springbootmailer.models.MyEmail;
import com.project.springbootmailer.models.SendResponse;

public interface EmailService {
    SendResponse sendEmail(MyEmail email) throws Exception;
}
