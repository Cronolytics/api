package com.cronolytics.api.dto.req;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SendMailDTO {
    @Email
    private String from;
    @Email
    @NotBlank
    private String to;
    private String subject;
    @NotBlank
    private String text;


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }
}
