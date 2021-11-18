package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.repository.GamesRepository;
import com.leverx.dealerstat.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamesServiceImpl implements GamesService {

    private final GamesRepository repository;

    @Autowired
    public GamesServiceImpl(GamesRepository repository) {
        this.repository = repository;
    }
}
