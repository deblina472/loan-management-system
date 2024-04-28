package com.cognizant.controller;

import com.cognizant.dto.BaseInterestRatesDTO;
import com.cognizant.services.classes.BaseInterestRatesServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BaseInterestRatesControllerTest {
    private MockMvc mockMvc;
    @Mock
    private BaseInterestRatesServiceImpl baseInterestRatesService;
    @InjectMocks
    private BaseInterestaRatesController baseInterestaRatesController;

    private MockRestServiceServer mockRestServiceServer;
    private RestTemplate template;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(baseInterestaRatesController).build();
        template = new RestTemplate();
        mockRestServiceServer = MockRestServiceServer.createServer(template);
    }

    @Test
    void testHandleGetInterestRateList_PositiveReturnValue(){
        try{
            List<BaseInterestRatesDTO> baseInterestRatesDTOList = new ArrayList<BaseInterestRatesDTO>();
            BaseInterestRatesDTO baseInterestRatesDTO = new BaseInterestRatesDTO();
            baseInterestRatesDTO.setId(10);
            baseInterestRatesDTO.setLoanType("Home");
            baseInterestRatesDTO.setBaseInterestRate(8.5f);
            baseInterestRatesDTOList.add(baseInterestRatesDTO);

            when(baseInterestRatesService.getInterestRateList()).thenReturn(baseInterestRatesDTOList);
            ResponseEntity<?> responseEntity = baseInterestaRatesController.handleGetInterestRateList();
            List<BaseInterestRatesDTO> actual = (List<BaseInterestRatesDTO>)responseEntity.getBody();
            assertEquals(1,actual.size());

        }catch(Exception e){
            assertTrue(false);
        }
    }
    @Test
    void testHandleGetInterestRateList_NegativeReturnValue(){
        try{
            List<BaseInterestRatesDTO> baseInterestRatesDTOList = new ArrayList<BaseInterestRatesDTO>();
            when(baseInterestRatesService.getInterestRateList()).thenReturn(baseInterestRatesDTOList);
            ResponseEntity<?> responseEntity = baseInterestaRatesController.handleGetInterestRateList();
            List<BaseInterestRatesDTO> actual = (List<BaseInterestRatesDTO>) responseEntity.getBody();
            assertTrue(actual.size()==0);
        }catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    void testUriHandleGetInterestRateList_Positive(){
        List<BaseInterestRatesDTO> baseInterestRatesDTOList = new ArrayList<BaseInterestRatesDTO>();
        when(baseInterestRatesService.getInterestRateList()).thenReturn(baseInterestRatesDTOList);
        try{
            MvcResult mvcResult = mockMvc
                    .perform(get("http://localhost:8098/api/interestrates"))
                    .andExpect(status().isOk())
                    .andReturn();

        }catch(Exception e){
            assertTrue(false);
        }
    }


}

