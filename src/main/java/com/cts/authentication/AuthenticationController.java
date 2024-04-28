package com.cognizant.authentication;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/authenticate")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class AuthenticationController {

    private AuthenticationServiceImpl authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("users")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        log.info(authenticationRequest.toString()+"authentication");
        AuthenticationDTO authenticationDTO = authenticationService.authenticateUser(authenticationRequest.getUserName(),authenticationRequest.getPassword());
        if(authenticationDTO.getUserName()!=null){
            return new ResponseEntity<AuthenticationDTO>(authenticationDTO, HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
