package com.leverx.dealerstat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender sender;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendMessage(String to) {
        //SimpleMailMessage message = new SimpleMailMessage();

    }
}
