package com.cognizant.authentication;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String userName;
    private String password;
    private String role;
}
