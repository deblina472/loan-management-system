package com.cognizant.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class LoanPlansHistoryDTO {
    private int id;
    private LocalDate updatedDate;
    private int loanPlanId;

}
