package com.leverx.dealerstat.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class GameObjectDTO {

    private String title;
    private String description;
    private Long authorId;
    private Long gameId;
    private BigDecimal price;

}
