package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.service.ConfirmationsService;
import com.leverx.dealerstat.service.MailSenderService;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private final MailSenderService senderService;
    private final UsersService usersService;
    private final ConfirmationsService confirmationsService;

    @Autowired
    public AuthorizationController(MailSenderService senderService,
                                   UsersService usersService,
                                   ConfirmationsService confirmationsService) {
        this.senderService = senderService;
        this.usersService = usersService;
        this.confirmationsService = confirmationsService;
    }


    @GetMapping("/confirm")
    public ResponseEntity<UserDTO> confirmRegistration(@RequestParam("code") String code) {
        User user = confirmationsService.findUserByCode(code);
        if (user == null || user.isConfirmed()) {
            return ResponseEntity.notFound().build();
        }
        usersService.confirm(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> recoverPassword(@RequestParam("email") String email) {
        User user = usersService.findByEmail(email);
        senderService.sendMessageToRecoverPassword(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/forgot_password/confirm")
    public ResponseEntity<?> confirmRecovering(@RequestParam("code") String code,
                                               @RequestParam("newPassword") String password) {
        User user = confirmationsService.findUserByCode(code);
        if (user == null || !user.isConfirmed()) {
            return ResponseEntity.notFound().build();
        }
        usersService.recoverPassword(user, password);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset")
    public ResponseEntity resetPassword(@RequestParam("code") String code,
                                        @RequestParam("password") String newPassword) {
        return null;
    }
}
