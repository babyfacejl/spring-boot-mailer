package com.project.springbootmailer.models;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class MyEmailTest {

    @Test
    public void shouldCheckRequiredFields() {
        final MyEmail email = new MyEmail();
        final List<String> errorMsgs = email.validate();
        assertThat(errorMsgs, hasItem("From is required"));
        assertThat(errorMsgs, hasItem("To is required"));
        assertThat(errorMsgs, hasItem("Subject is required"));
        assertThat(errorMsgs, hasItem("Body is required"));
    }

    @Test
    public void shouldCheckInvalidEmails() {
        final MyEmail email = new MyEmail("hell@.com", "to@.com", "cc1@world.com,cc2@.com",
                "bcc1@world.com,bcc2@.com","text", "subject");
        final List<String> errorMsgs = email.validate();
        assertThat(errorMsgs, hasItem("From contains invalid email address"));
        assertThat(errorMsgs, hasItem("To contains invalid email address"));
        assertThat(errorMsgs, hasItem("Cc contains invalid email address"));
        assertThat(errorMsgs, hasItem("Bcc contains invalid email address"));
    }

    @Test
    public void shouldValidate() {
        final MyEmail email = new MyEmail("hell@world.com", "to@world.com", "cc1@world.com,cc2@world.com",
                "bcc1@world.com,bcc2@world.com","text", "subject");
        final List<String> errorMsgs = email.validate();
        assertThat(email.validate().size(), CoreMatchers.is(0));
    }
}