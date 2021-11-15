package com.leverx.dealerstat.dto;

import com.leverx.dealerstat.model.Game;
import com.leverx.dealerstat.model.GameObject;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class GameDTO {

    private Long id;
    private String name;
    private List<GameObjectDTO> gameObjects;

    public static GameDTO toGameDTO(Game game) {
        return GameDTO.builder()
                .id(game.getId())
                .name(game.getName())
                .gameObjects(game.getGameObjects().stream()
                        .map(GameObjectDTO::toGameObjectDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
