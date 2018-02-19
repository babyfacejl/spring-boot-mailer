package com.project.springbootmailer.controllers;

import com.project.springbootmailer.models.MyEmail;
import com.project.springbootmailer.services.SendEmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RestControllerTest {
    @Mock
    private SendEmailService emailService;

    private RestController controller;

    @Before
    public void setUp() throws Exception {
        controller = new RestController(emailService);
    }

    @Test
    public void shouldInvokeSend() throws Exception {
        final MyEmail email = new MyEmail("hell@world.com", "to@world.com", "cc1@world.com,cc2@world.com",
                "bcc1@world.com,bcc2@world.com","text", "subject");
        controller.send(email);
        Mockito.verify(emailService, Mockito.times(1)).send(email);
    }
}