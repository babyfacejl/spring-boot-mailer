package com.project.springbootmailer.utils;

import com.sendgrid.Email;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MultipleEmailsConverterTest {

    @Test
    public void shouldConvertEmail() {
        List<Email> actual = MultipleEmailsConverter.convertEmails("test@testexample.com");
        assertThat(actual.size(), is(1));
        assertThat(actual.get(0).getEmail(), is("test@testexample.com"));
    }
}