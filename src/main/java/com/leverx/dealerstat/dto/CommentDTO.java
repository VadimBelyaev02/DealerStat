package com.leverx.dealerstat.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentDTO {

    private Long id;
    private String message;
    private Float rate;
    private Date creatingDate;
    private Boolean approved;


}
