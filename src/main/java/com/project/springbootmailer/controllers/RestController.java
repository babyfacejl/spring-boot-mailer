package com.project.springbootmailer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springbootmailer.models.MyEmail;
import com.project.springbootmailer.models.SendResponse;
import com.project.springbootmailer.services.SendEmailService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private static final Logger logger = Logger.getLogger(RestController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final SendEmailService sendEmailService;

    @Autowired
    public RestController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(@RequestBody MyEmail email) throws Exception {
        logger.debug(email.toString());
        SendResponse response = sendEmailService.send(email);
        return objectMapper.writeValueAsString(response);
    }


}