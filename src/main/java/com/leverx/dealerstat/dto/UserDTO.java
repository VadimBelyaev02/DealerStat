package com.leverx.dealerstat.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@Builder
public class UserDTO {

    private String firstName;
    private String lastName;
    private String password;
    private String email;

}
