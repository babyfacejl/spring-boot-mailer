package com.project.springbootmailer.services;

import com.project.springbootmailer.models.MyEmail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailServiceTest {
    @Mock
    private EmailService primaryEmailService;
    @Mock
    private EmailService backupEmailService;
    private SendEmailService sendEmailService;

    @Before
    public void setUp() throws Exception {
        sendEmailService = new SendEmailService(primaryEmailService, backupEmailService);
    }

    @Test
    public void shouldInvokePrimaryService() throws Exception {
        final MyEmail email = new MyEmail("hell@world.com", "to@world.com", "cc1@world.com,cc2@world.com",
                "bcc1@world.com,bcc2@world.com","text", "subject");
        sendEmailService.send(email);
        verify(primaryEmailService, times(1)).sendEmail(email);
        verifyZeroInteractions(backupEmailService);

    }
}