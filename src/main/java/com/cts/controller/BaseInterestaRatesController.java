package com.cognizant.controller;

import com.cognizant.services.classes.BaseInterestRatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Deblina Das
 * This class represents the end point for getting base interest rates
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class BaseInterestaRatesController {

    private BaseInterestRatesServiceImpl baseInterestRatesService;
    @Autowired
    public BaseInterestaRatesController(BaseInterestRatesServiceImpl baseInterestRatesService) {
        this.baseInterestRatesService = baseInterestRatesService;
    }

    /**
     * End point to get list of base interest rates
     * @return response containing list of base interest rates
     */
    @GetMapping("/interestrates")
    public ResponseEntity<?> handleGetInterestRateList(){

        return new ResponseEntity<>(baseInterestRatesService.getInterestRateList(), HttpStatus.OK);
    }
}
