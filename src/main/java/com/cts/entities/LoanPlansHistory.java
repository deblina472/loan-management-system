package com.cts.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * @author Deblina Das
 * This is an Entity class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Loan_Plans_History")
public class LoanPlansHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Updated_Date")
    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "Loan_Plan_Id")
    private LoanPlans loanPlans;


}
