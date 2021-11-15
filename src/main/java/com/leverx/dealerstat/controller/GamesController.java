package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GamesController {

    private final GamesService service;

    @Autowired
    public GamesController(GamesService service) {
        this.service = service;
    }
}
