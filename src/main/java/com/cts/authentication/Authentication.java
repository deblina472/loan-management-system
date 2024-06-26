package com.cognizant.authentication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class Authentication {

    @Id
    @Column(name="user_name")
    private String userName;
    @Column(name="password")
    private String password;
    @Column(name="role")
    private String role;
}
