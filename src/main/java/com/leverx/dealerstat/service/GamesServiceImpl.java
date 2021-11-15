package com.leverx.dealerstat.service;

import com.leverx.dealerstat.repository.GamesRepository;
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
