package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.exception.MessageSendingException;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender sender) {
        this.mailSender = sender;
    }

    @Override
    public void sendVerificationCode(User user)  {
        String fromAddress = from;
        String senderName = "DealerStat";
        String subject = "Please verify your registration";
        String siteURL = "http://localhost";
        String fullName = user.getFirstName() + " " + user.getLastName();
        String verifyURL = siteURL + "/verify?code=" + user.getConfirmation().getCode();

        String content = "Dear " + fullName + ",<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<a href=\"" + verifyURL + "\">VERIFY</a><br>"
                + "Thank you,<br>"
                + "DealerStat.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String toAddress = user.getEmail();
        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new MessageSendingException("Error sending the message");
        }
    }

    @Override
    public void sendMessageToRecoverPassword(User user) {

    }
}
