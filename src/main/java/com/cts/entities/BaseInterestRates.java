package com.cts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Deblina Das
 * This is an Entity class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Base_Interest_Rates")
public class BaseInterestRates {
    @Id
    @Column(name = "Id")
    private int id;
    @Column(name = "Loan_Type")
    private String loanType;
    @Column(name = "Base_Interest_Rate")
    private float baseInterestRate;


}
