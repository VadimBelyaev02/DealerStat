package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.converter.GamesConverter;
import com.leverx.dealerstat.dto.GameDTO;
import com.leverx.dealerstat.model.Game;
import com.leverx.dealerstat.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GamesController {

    private final GamesService gamesService;
    private final GamesConverter converter;

    @Autowired
    public GamesController(GamesService gamesService, GamesConverter converter) {
        this.gamesService = gamesService;
        this.converter = converter;
    }

    @GetMapping("/games")
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> gameDTOS = gamesService.findAll().stream().map(converter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(gameDTOS);
    }

    @PostMapping("/games")
    public ResponseEntity<GameDTO> addGame(@RequestBody GameDTO gameDTO) {
        Game game = gamesService.save(converter.convertToModel(gameDTO));
        return ResponseEntity.ok(converter.convertToDTO(game));
    }

    @PutMapping("/games/{id}")
    public ResponseEntity<?> updateGame(@RequestBody GameDTO gameDTO,
                                        @PathVariable("id") Long id) {
        // get authorities to update. Is it the user that has the game?
      //  gamesService.update(converter.convertToModel(gameDTO));
        return ResponseEntity.ok().build();
    }

}
