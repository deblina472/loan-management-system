package com.cts.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Loan_Plans")
public class LoanPlans {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Plan_Id")
    private int planId;

    @Column(name = "Plan_Name")
    private String planName;

    @Column(name = "Principle_Amount")
    private int principleAmount;
    @Column(name = "Tenure")
    private int tenure;
    @Column(name = "Interest_Rate")
    private float interestRate;
    @Column(name = "Interest_Amount")
    private int interestAmount;
    @Column(name = "Total_Payable")
    private int totalPayable;
    @Column(name = "EMI")
    private float emi;
    @Column(name = "Plan_Validity")
    private LocalDate planValidity;
    @Column(name = "Plan_Added_On")
    private LocalDate planAddedOn;


    @ManyToOne
    @JoinColumn(name = "Id")
    private BaseInterestRates baseInterestRates;

}
