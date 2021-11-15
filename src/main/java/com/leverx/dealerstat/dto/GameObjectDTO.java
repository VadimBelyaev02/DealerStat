package com.leverx.dealerstat.dto;

import com.leverx.dealerstat.model.GameObject;
import com.leverx.dealerstat.model.Status;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class GameObjectDTO {

    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private UserDTO author;
    private Calendar dateOfCreating;
    private Calendar dateOfUpdating;
    private BigDecimal price;
    private List<CommentDTO> comments;
    private List<GameDTO> games;

    public static GameObjectDTO toGameObjectDTO(GameObject gameObject) {
        return GameObjectDTO.builder()
                .id(gameObject.getId())
                .title(gameObject.getTitle())
                .description(gameObject.getDescription())
                .status(gameObject.getStatus())
                .author(UserDTO.toUserDTO(gameObject.getAuthor()))
                .dateOfCreating(gameObject.getDateOfCreating())
                .dateOfUpdating(gameObject.getDateOfUpdating())
                .price(gameObject.getPrice())
                .comments(gameObject.getComments().stream()
                        .map(CommentDTO::toCommentDTO)
                        .collect(Collectors.toList()))
                .games(gameObject.getGames().stream()
                        .map(GameDTO::toGameDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
