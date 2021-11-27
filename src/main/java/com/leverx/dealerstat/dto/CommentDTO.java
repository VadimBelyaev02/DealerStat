package com.leverx.dealerstat.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Builder
public class CommentDTO {

    private String message;
    private Double rate;
    private Long userId;
    private Long authorId;
    private Date createdAt;
}
