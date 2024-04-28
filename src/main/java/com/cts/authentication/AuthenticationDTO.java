package com.cognizant.authentication;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String userName;
    private String password;
    private String role;

}
