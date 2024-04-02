package com.cognizant.services;


import com.cognizant.dto.LoanPlansHistoryDTO;
import com.cognizant.repositories.LoanPlansHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanPlansHistoryServiceImpl implements LoanPlansHistoryService {
    @Autowired
    private LoanPlansHistoryRepository loanPlansHistoryRepository;

    private LoanPlansHistoryDTO loanPlansHistoryDTO;


}
