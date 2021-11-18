package com.leverx.dealerstat.converter;

import com.leverx.dealerstat.dto.CommentDTO;
import com.leverx.dealerstat.dto.GameObjectDTO;
import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.model.Comment;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommentsConverter {

    public CommentDTO convertToDTO(final Comment comment) {
        final Long id = comment.getId();
        final String message = comment.getMessage();
        final Float rate = comment.getRate();
        final Date date = comment.getCreatingDate();
        final boolean approved = comment.getApproved();
        return CommentDTO.builder()
                .id(id)
                .message(message)
                .rate(rate)
                .creatingDate(date)
                .approved(approved)
                .build();
    }
}


