package com.cronolytics.api.service;

import com.cronolytics.api.dto.req.SendMailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void submit(SendMailDTO emailDTO) throws MailException {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(emailDTO.getFrom());
        email.setTo(emailDTO.getTo());
        email.setSubject(emailDTO.getSubject());
        email.setText(emailDTO.getText());
        javaMailSender.send(email);
    }
}
