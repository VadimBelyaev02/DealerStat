package com.leverx.dealerstat.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class GameObjectDTO {

    private Long id;
    private String title;
    private String description;
    private UserDTO author;
    private Date dateOfCreating;
    private Date dateOfUpdating;
    private BigDecimal price;

}
