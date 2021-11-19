package com.leverx.dealerstat.service;

import com.leverx.dealerstat.exception.MessageSendingException;
import com.leverx.dealerstat.model.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface MailSenderService {

    void sendVerificationCode(User user);

    void sendMessageToRecoverPassword(User user);

}
