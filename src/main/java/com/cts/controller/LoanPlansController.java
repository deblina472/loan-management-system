package com.cognizant.controller;

import com.cognizant.dto.LoanPlansDTO;
import com.cognizant.services.classes.LoanPlansServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Deblina Das
 * This class represents the end points for performing operations
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class LoanPlansController {
    @Autowired
    private LoanPlansServiceImpl loanPlansService;

    /**
     *End point for adding new loan plan
     * @param loanPlansDTO
     * @return
     */
    @PostMapping("/addloanplans")
    public ResponseEntity<?> insertNewPlan(@Valid @RequestBody LoanPlansDTO loanPlansDTO){

        loanPlansService.addNewPlan(loanPlansDTO);
        if(loanPlansDTO.getPlanId()!=0){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * End point to get all loan plans available
     * @return response of list of loan plans
     */
    @GetMapping("/loanplans")
    public ResponseEntity<?> handleGetLoanPlan(){
        List<LoanPlansDTO> responseList = loanPlansService.fetchLoanPlans();
        ResponseEntity<List<LoanPlansDTO>> responseEntity = null;
        if(!responseList.isEmpty()){
            responseEntity = new ResponseEntity<List<LoanPlansDTO>>(responseList, HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    /**
     * End point for getting loan plan
     * @param planId
     * @return response of loan plan getting by Id
     */
    @GetMapping("loanplans/{planId}")
    public ResponseEntity<?> handleGetLoanPlanById(@PathVariable("planId") int planId){
        LoanPlansDTO response = loanPlansService.fetchLoanPlanById(planId);
        ResponseEntity<LoanPlansDTO> responseEntity = null;
        if(response.getPlanId()!=0){
            responseEntity = new ResponseEntity<LoanPlansDTO>(response,HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * End point for updating loan plan
     * @param planId
     * @param loanPlansDTO
     * @return response status
     */
    @PutMapping("updateloanplans/{planId}")
    public ResponseEntity<?> handleUpdateLoanPlan(@PathVariable("planId") int planId, @RequestBody LoanPlansDTO loanPlansDTO){
        String response = loanPlansService.updateLoanPlan(planId,loanPlansDTO);
        if(response.equals("success")){
            return new ResponseEntity<>("Plan Updated",HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }



}
