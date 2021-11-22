package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.converter.GameObjectsConverter;
import com.leverx.dealerstat.dto.GameObjectDTO;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.service.GameObjectService;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameObjectsController {

    private final GameObjectsConverter gameObjectsConverter;
    private final UsersService usersService;
    private final GameObjectService gameObjectService;

    @Autowired
    public GameObjectsController(GameObjectsConverter gameObjectsConverter,
                                 UsersService usersService,
                                 GameObjectService gameObjectService) {
        this.gameObjectsConverter = gameObjectsConverter;
        this.usersService = usersService;
        this.gameObjectService = gameObjectService;
    }


    @PutMapping("/objects/{id}")
    public ResponseEntity<GameObjectDTO> editGameObject(@RequestBody GameObjectDTO gameObject,
                                                        @PathVariable("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = usersService.findByEmail(email);
        if (!gameObjectService.findById(id).getAuthor().getId().equals(user.getId())) {
            return ResponseEntity.notFound().build();
        }
     //   gameObjectService.update(gameObjectsConverter.convertToModel(gameObject));
        return null;
    }

    @PostMapping("/objects")
    public ResponseEntity<GameObjectDTO> addGameObject(@RequestBody GameObjectDTO gameObject) {
        gameObjectService.save(gameObjectsConverter.convertToModel(gameObject));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/objects")
    public ResponseEntity<List<GameObjectDTO>> getGameObjects() {
        List<GameObjectDTO> gameObjectsDTO = gameObjectService.findAll().stream()
                .map(gameObjectsConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(gameObjectsDTO);
    }

    @GetMapping("/objects/my")
    public ResponseEntity<List<GameObjectDTO>> getAuthorizedUserGameObjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = usersService.findByEmail(email);
        List<GameObjectDTO> gameObjectDTOS = gameObjectService.findAllByAuthorId(user.getId())
                .stream().map(gameObjectsConverter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(gameObjectDTOS);
    }


}
