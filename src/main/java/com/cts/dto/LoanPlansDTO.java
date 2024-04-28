package com.cognizant.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanPlansDTO {
    @NotNull
    private int planId;
    @NotBlank
    private String planName;
    @NotNull
    private int principleAmount;
    private int tenure;
    @NotNull
    private float interestRate;
    @NotNull
    private int interestAmount;
    @NotNull
    private int totalPayable;
    @NotNull
    private float emi;
    @Future(message="Please enter future date")
    private LocalDate planValidity;
    private LocalDate planAddedOn = LocalDate.now();

    private int loanTypeId;
    private String loanType;
    private float baseInterestRate;

//    private BaseInterestRatesDTO baseInterestRatesDTO;

//    private String status;
}
