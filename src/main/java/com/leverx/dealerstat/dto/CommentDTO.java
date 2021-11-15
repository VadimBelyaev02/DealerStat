package com.leverx.dealerstat.dto;

import com.leverx.dealerstat.model.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

@Data
@Builder
public class CommentDTO {

    private Long id;
    private String message;
    private GameObjectDTO gameObject;
    private UserDTO user;
    private Calendar dateOfCreating;
    private Float mark;

    public static CommentDTO toCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .message(comment.getMessage())
                .gameObject(GameObjectDTO.toGameObjectDTO(comment.getGameObject()))
                .user(UserDTO.toUserDTO(comment.getUser()))
                .dateOfCreating(comment.getDateOfCreating())
                .mark(comment.getMark())
                .build();
    }
}
