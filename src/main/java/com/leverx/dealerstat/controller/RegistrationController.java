package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.converter.UsersConverter;
import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.exception.AlreadyExistsException;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.service.ConfirmationsService;
import com.leverx.dealerstat.service.MailSenderService;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private final UsersService usersService;
    private final UsersConverter converter;
    private final ConfirmationsService confirmationsService;
    private final MailSenderService mailSenderService;

    @Autowired
    public RegistrationController(UsersService usersService,
                                  UsersConverter converter,
                                  ConfirmationsService confirmationsService,
                                  MailSenderService mailSenderService) {
        this.usersService = usersService;
        this.converter = converter;
        this.confirmationsService = confirmationsService;
        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO user) {
        User userEntity = converter.convertToModel(user);
        try {
            usersService.save(userEntity);
            confirmationsService.save(userEntity);
            mailSenderService.sendVerificationCode(userEntity);
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(user);
    }


}
