package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.exception.AlreadyExistsException;
import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.Game;
import com.leverx.dealerstat.repository.GamesRepository;
import com.leverx.dealerstat.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesServiceImpl implements GamesService {

    private final GamesRepository repository;

    @Autowired
    public GamesServiceImpl(GamesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }

    @Override
    public Game save(Game game) {
        return repository.save(game);
    }

    @Override
    public void update(Game game, Long id) {
        if (repository.existsByName(game.getName())) {
            throw new AlreadyExistsException("The game already exists");
        }

        Game gameFromDB = repository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Game is not found");
        });
        gameFromDB.setName(game.getName());
        //gameFromDB.set
        // fuck i am so tired. i'll do it as soon as i'll be able
    }
}
