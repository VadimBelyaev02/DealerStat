package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.repository.UsersRepository;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    private final UsersService service;

    @Autowired
    public UsersController(UsersService service) {
        this.service = service;
    }


}
