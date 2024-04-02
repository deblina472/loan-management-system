package com.cognizant.services;

import com.cognizant.dto.LoanPlansDTO;

import java.util.List;

public interface LoanPlansService {


    public String addNewPlan(LoanPlansDTO loanPlansDTO);

    public List<LoanPlansDTO> fetchLoanPlans();

    public LoanPlansDTO fetchLoanPlanById(int planId);

    public String updateLoanPlan(int planId, LoanPlansDTO loanPlansDTO);
}
