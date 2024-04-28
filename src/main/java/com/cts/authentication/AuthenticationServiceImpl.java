package com.cognizant.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private AuthenticationRepository authenticationRepository;
    @Override
    public List<Authentication> listOfUsers() {

        return (List<Authentication>) authenticationRepository.findAll();
    }

    @Override
    public AuthenticationDTO authenticateUser(String username, String password) {
        List<Authentication> authentications = listOfUsers();
        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        for(Authentication authentication : authentications){
            if(authentication.getUserName().equals(username) && authentication.getPassword().equals(password)){
                authenticationDTO.setUserName(authentication.getUserName());
                authenticationDTO.setPassword(authentication.getPassword());
                authenticationDTO.setRole(authentication.getRole());
                break;
            }
        }
        return authenticationDTO;
    }
}
