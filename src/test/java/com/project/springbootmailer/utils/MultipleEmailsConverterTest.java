package com.project.springbootmailer.utils;

import com.project.springbootmailer.models.MyEmail;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Personalization;
import org.junit.Test;

import java.util.List;

import static com.project.springbootmailer.utils.MultipleEmailsConverter.convertEmails;
import static com.project.springbootmailer.utils.MultipleEmailsConverter.convertToSendgridMail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultipleEmailsConverterTest {

    @Test
    public void shouldConvertStringToEmail() {
        final List<Email> actual = convertEmails("test@testexample.com");
        assertThat(actual.size(), is(1));
        assertThat(actual.get(0).getEmail(), is("test@testexample.com"));
    }

    @Test
    public void shouldConvertStringToEmails() {
        final List<Email> actual = convertEmails("test@testexample.com,test@testexample1.com");
        assertThat(actual.size(), is(2));
        assertThat(actual.get(0).getEmail(), is("test@testexample.com"));
        assertThat(actual.get(1).getEmail(), is("test@testexample1.com"));
    }

    @Test
    public void shouldConvertToSendgridEmail() {
        final MyEmail email = new MyEmail("hell@world.com", "to@world.com", "cc1@world.com,cc2@world.com",
                "bcc1@world.com,bcc2@world.com","text", "subject");
        final Mail mail = convertToSendgridMail(email);

        assertThat(mail.getSubject(), is(email.getSubject()));
        assertThat(mail.getContent().get(0).getValue(), is(email.getText()));
        final List<Personalization> personalizations = mail.getPersonalization();
        assertThat(personalizations.size(), is(1));
        final Personalization personalization = personalizations.get(0);

        assertThat(personalization.getTos().get(0).getEmail(), is("to@world.com"));

        assertThat(personalization.getCcs().size(), is(2));
        assertThat(personalization.getCcs().get(0).getEmail(), is("cc1@world.com"));
        assertThat(personalization.getCcs().get(1).getEmail(), is("cc2@world.com"));

        assertThat(personalization.getBccs().size(), is(2));
        assertThat(personalization.getBccs().get(0).getEmail(), is("bcc1@world.com"));
        assertThat(personalization.getBccs().get(1).getEmail(), is("bcc2@world.com"));
    }
}