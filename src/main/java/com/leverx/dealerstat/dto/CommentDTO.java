package com.leverx.dealerstat.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentDTO {

    private String message;
    private Float rate;
    private Long gameObjectId;
    private Long authorId;

}
