package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.dto.GameObjectDTO;
import com.leverx.dealerstat.model.GameObject;
import com.leverx.dealerstat.service.GameObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameObjectsController {

    private final GameObjectService service;

    @Autowired
    public GameObjectsController(GameObjectService service) {
        this.service = service;
    }

    @PutMapping("/object/{id}")
    public ResponseEntity<GameObjectDTO> editGameObject(@RequestBody GameObject gameObject,
                                                        @PathVariable("id") Long id) {

        return null;
    }

    @PostMapping("/object")
    public ResponseEntity<GameObjectDTO> addGameObject(@Valid @RequestBody GameObject gameObject) {

        return null;
    }

    @GetMapping("/object")
    public ResponseEntity<List<GameObjectDTO>> getGameObjects() {
        return null;
    }

    @GetMapping("/my")
    public ResponseEntity<List<GameObjectDTO>> getAuthorizedUserGameObjects() {
        return null;
    }


}
