package com.leverx.dealerstat.service;

import com.leverx.dealerstat.model.Game;

import java.util.List;

public interface GamesService {

    List<Game> findAll();

    Game save(Game convertToModel);

    void update(Game game, Long id);
}
