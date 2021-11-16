package com.leverx.dealerstat.dto;

import com.leverx.dealerstat.model.Confirmation;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ConfirmationDTO {

    private Long id;
    private String hashCode;
    private Date expirationTime;
    private UserDTO user;

    public static ConfirmationDTO toConfirmationDTO(Confirmation confirmation) {
        return ConfirmationDTO.builder()
                .id(confirmation.getId())
                .hashCode(confirmation.getHashCode())
                .expirationTime(confirmation.getExpirationTime())
                .user(UserDTO.toUserDTO(confirmation.getUser()))
                .build();
    }
}
