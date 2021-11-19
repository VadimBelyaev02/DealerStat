package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.service.MailSenderService;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final MailSenderService senderService;
    private final UsersService usersService;

    public AuthorizationController(MailSenderService senderService, UsersService usersService) {
        this.senderService = senderService;
        this.usersService = usersService;
    }


    @PostMapping("/forgot_password")
    public ResponseEntity recoverPassword(@RequestParam("email") String email) {
        User user = usersService.findByEmail(email);
        senderService.sendMessageToRecoverPassword(user);
        return null;
    }

    @PostMapping("/reset")
    public ResponseEntity resetPassword(@RequestParam("code") String code,
                                        @RequestParam("password") String newPassword) {
        return null;
    }
}
