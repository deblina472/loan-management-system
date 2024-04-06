package com.cognizant.controller;

import com.cognizant.services.classes.BaseInterestRatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BaseInterestaRatesController {
    @Autowired
    private BaseInterestRatesServiceImpl baseInterestRatesService;
    @GetMapping("/interestrates")
    public ResponseEntity<?> handleGetInterestRateList(){

        return new ResponseEntity<>(baseInterestRatesService.getInterestRateList(), HttpStatus.OK);
    }
}
