package com.cognizant.authentication;

import java.util.List;


public interface AuthenticationService {
    public List<Authentication> listOfUsers();
    public AuthenticationDTO authenticateUser(String username,String password);

}
