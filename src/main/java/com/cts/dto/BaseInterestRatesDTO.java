package com.cognizant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseInterestRatesDTO {
    @NotNull
    private int id;
    @NotBlank
    private String loanType;
    @NotNull
    private float baseInterestRate;

}
