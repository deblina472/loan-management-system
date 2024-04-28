package com.cognizant.main;

import com.cognizant.LoanPlansModuleApplication;
import com.cognizant.controller.BaseInterestaRatesController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = LoanPlansModuleApplication.class)
class LoanPlansModuleApplicationTests {
    @Autowired
    private BaseInterestaRatesController baseInterestaRatesController;

    @Test
    void contextLoads() {
        assertNotNull(baseInterestaRatesController);
    }

}
