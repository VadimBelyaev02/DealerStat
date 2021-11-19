package com.leverx.dealerstat.dto;

import com.leverx.dealerstat.model.Confirmation;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ConfirmationDTO {

    private String code;
    private Date expirationTime;

}
