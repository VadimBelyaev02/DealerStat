package com.leverx.dealerstat.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameDTO {

    private Long id;
    private String name;

}
