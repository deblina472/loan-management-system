package com.cognizant.controller;

import com.cognizant.dto.LoanPlansDTO;
import com.cognizant.services.interfaces.LoanPlansService;
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
    private LoanPlansService loanPlansService;

    /**
     *
     * @param loanPlansDTO
     * @return
     */
    @PostMapping("/loanplans")
    public ResponseEntity<?> insertNewPlan(@Valid @RequestBody LoanPlansDTO loanPlansDTO){
        loanPlansService.addNewPlan(loanPlansDTO);
        if(loanPlansDTO.getStatus().equals("success")){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

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

    @GetMapping("loanplans/{planId}")
    public ResponseEntity<?> handleGetLoanPlanById(@PathVariable("planId") int planId){
        LoanPlansDTO response = loanPlansService.fetchLoanPlanById(planId);
        ResponseEntity<LoanPlansDTO> responseEntity = null;
        if(response.getPlanId()!=0){
            responseEntity = new ResponseEntity<LoanPlansDTO>(response,HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @PutMapping("loanplans/{planId}")
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
