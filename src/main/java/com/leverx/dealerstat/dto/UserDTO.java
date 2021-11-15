package com.leverx.dealerstat.dto;

import com.leverx.dealerstat.model.Role;
import com.leverx.dealerstat.model.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Calendar dateOfCreating;
    @Enumerated(EnumType.STRING)
    private Role role;
    private List<CommentDTO> comments;
    private List<GameObjectDTO> gameObjects;

    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirst_name())
                .lastName(user.getLast_name())
                .email(user.getEmail())
                .dateOfCreating(user.getDateOfCreating())
                .role(user.getRole())
                .comments(user.getComments().stream()
                        .map(CommentDTO::toCommentDTO)
                        .collect(Collectors.toList()))
                .gameObjects(user.getGameObjects().stream()
                        .map(GameObjectDTO::toGameObjectDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}