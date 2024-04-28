package com.cognizant.controller;

import com.cognizant.dto.LoanPlansDTO;
import com.cognizant.services.classes.LoanPlansServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
class LoanPlansControllerTest {
    private MockMvc mockMvc;
    private MockRestServiceServer mockRestServiceServer;
    private RestTemplate template;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private LoanPlansServiceImpl loanPlansService;
    @InjectMocks
    private LoanPlansController loanPlansController;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loanPlansController).build();
        template = new RestTemplate();
        mockRestServiceServer = MockRestServiceServer.createServer(template);
    }

    @Test
    void testInsertNewPlan_PositiveReturnValue(){
        LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
        loanPlansDTO.setPlanId(10);
        loanPlansDTO.setPlanName("Home Loan");
        loanPlansDTO.setPrincipleAmount(100000);
        loanPlansDTO.setTenure(48);
        loanPlansDTO.setInterestRate(8.5f);
        loanPlansDTO.setInterestAmount(34000);
        loanPlansDTO.setTotalPayable(134000);
        loanPlansDTO.setEmi(2791.6667f);
        loanPlansDTO.setPlanValidity(LocalDate.of(2028, Month.APRIL,22));
        loanPlansDTO.setPlanAddedOn(LocalDate.now());
        loanPlansDTO.setLoanTypeId(1);
        loanPlansDTO.setLoanType("Home");
        loanPlansDTO.setBaseInterestRate(8.5f);
        when(loanPlansService.addNewPlan(loanPlansDTO)).thenReturn(loanPlansDTO);

        ResponseEntity<?> responseEntity = loanPlansController.insertNewPlan(loanPlansDTO);
        assertEquals(201,responseEntity.getStatusCodeValue());
    }

    @Test
    void testInsertNewPlan_NegativeReturnValue(){
        LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
        when(loanPlansService.addNewPlan(loanPlansDTO)).thenReturn(null);

        ResponseEntity<?> responseEntity = loanPlansController.insertNewPlan(loanPlansDTO);
        assertEquals(400,responseEntity.getStatusCodeValue());
    }

    @Test
    void testHandleGetLoanPlan_PositiveReturnValue(){
        try{
            List<LoanPlansDTO> loanPlansDTOList = new ArrayList<LoanPlansDTO>();
            LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
            loanPlansDTO.setPlanId(10);
            loanPlansDTO.setPlanName("Home Loan");
            loanPlansDTO.setPrincipleAmount(100000);
            loanPlansDTO.setTenure(48);
            loanPlansDTO.setInterestRate(8.5f);
            loanPlansDTO.setInterestAmount(34000);
            loanPlansDTO.setTotalPayable(134000);
            loanPlansDTO.setEmi(2791.6667f);
            loanPlansDTO.setPlanValidity(LocalDate.of(2028, Month.APRIL,22));
            loanPlansDTO.setPlanAddedOn(LocalDate.now());
            loanPlansDTO.setLoanTypeId(1);
            loanPlansDTO.setLoanType("Home");
            loanPlansDTO.setBaseInterestRate(8.5f);
            loanPlansDTOList.add(loanPlansDTO);

            when(loanPlansService.fetchLoanPlans()).thenReturn(loanPlansDTOList);
            ResponseEntity<?> responseEntity = loanPlansController.handleGetLoanPlan();
            List<LoanPlansDTO> actual = (List<LoanPlansDTO>) responseEntity.getBody();
            assertEquals(1,actual.size());


        }catch(Exception e){
            assertTrue(false);
        }
    }
    @Test
    void testHandleGetLoanPlan_NegativeReturnValue(){
        try{
            List<LoanPlansDTO> loanPlansDTOList = new ArrayList<LoanPlansDTO>();
            when(loanPlansService.fetchLoanPlans()).thenReturn(loanPlansDTOList);
            ResponseEntity<?> responseEntity = loanPlansController.handleGetLoanPlan();
            assertNull(responseEntity.getBody());

        }catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    void testHandleGetLoanPlanById_PositiveReturnValue(){
        try{
            LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
            loanPlansDTO.setPlanId(10);
            loanPlansDTO.setPlanName("Home Loan");
            loanPlansDTO.setPrincipleAmount(100000);
            loanPlansDTO.setTenure(48);
            loanPlansDTO.setInterestRate(8.5f);
            loanPlansDTO.setInterestAmount(34000);
            loanPlansDTO.setTotalPayable(134000);
            loanPlansDTO.setEmi(2791.6667f);
            loanPlansDTO.setPlanValidity(LocalDate.of(2028, Month.APRIL,22));
            loanPlansDTO.setPlanAddedOn(LocalDate.now());
            loanPlansDTO.setLoanTypeId(1);
            loanPlansDTO.setLoanType("Home");
            loanPlansDTO.setBaseInterestRate(8.5f);

            when(loanPlansService.fetchLoanPlanById(10)).thenReturn(loanPlansDTO);
            ResponseEntity<?> responseEntity = loanPlansController.handleGetLoanPlanById(10);
            assertEquals(200,responseEntity.getStatusCodeValue());

        }catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    void testHandleGetLoanPlanById_NegativeReturnValue(){
        try{
            when(loanPlansService.fetchLoanPlanById(1)).thenReturn(null);
            ResponseEntity<?> responseEntity = loanPlansController.handleGetLoanPlan();
            assertNull(responseEntity.getBody());

        }catch(Exception e){
            assertTrue(false);
        }
    }

    @Test
    void testHandleUpdateLoanPlan_Accepted(){
        LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
        loanPlansDTO.setPlanId(10);
        loanPlansDTO.setPlanName("Home Loan");
        loanPlansDTO.setPrincipleAmount(100000);
        loanPlansDTO.setTenure(48);
        loanPlansDTO.setInterestRate(8.5f);
        loanPlansDTO.setInterestAmount(34000);
        loanPlansDTO.setTotalPayable(134000);
        loanPlansDTO.setEmi(2791.6667f);
        loanPlansDTO.setPlanValidity(LocalDate.of(2028, Month.APRIL,22));
        loanPlansDTO.setPlanAddedOn(LocalDate.now());
        loanPlansDTO.setLoanTypeId(1);
        loanPlansDTO.setLoanType("Home");
        loanPlansDTO.setBaseInterestRate(8.5f);

        when(loanPlansService.updateLoanPlan(10,loanPlansDTO)).thenReturn("success");
        ResponseEntity<?> responseEntity = loanPlansController.handleUpdateLoanPlan(10,loanPlansDTO);
        assertEquals(202,responseEntity.getStatusCodeValue());

    }
    @Test
    void testHandleUpdateLoanPlan_NotModified(){
        LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
        loanPlansDTO.setPlanId(10);
        loanPlansDTO.setPlanName("Home Loan");
        loanPlansDTO.setPrincipleAmount(100000);
        loanPlansDTO.setTenure(48);
        loanPlansDTO.setInterestRate(8.5f);
        loanPlansDTO.setInterestAmount(34000);
        loanPlansDTO.setTotalPayable(134000);
        loanPlansDTO.setEmi(2791.6667f);
        loanPlansDTO.setPlanValidity(LocalDate.of(2028, Month.APRIL,22));
        loanPlansDTO.setPlanAddedOn(LocalDate.now());
        loanPlansDTO.setLoanTypeId(1);
        loanPlansDTO.setLoanType("Home");
        loanPlansDTO.setBaseInterestRate(8.5f);

        when(loanPlansService.updateLoanPlan(10,loanPlansDTO)).thenReturn("fail");
        ResponseEntity<?> responseEntity = loanPlansController.handleUpdateLoanPlan(10,loanPlansDTO);
        assertEquals(304,responseEntity.getStatusCodeValue());

    }

}
