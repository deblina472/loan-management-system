package com.cognizant.repository;

import com.cognizant.LoanPlansModuleApplication;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.entities.LoanPlans;
import com.cognizant.repositories.LoanPlansRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = LoanPlansModuleApplication.class)
public class LoanPlansRepositoryTest {
    @Autowired
    private LoanPlansRepository loanPlansRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindAllPositive() {
        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setId(10);
        baseInterestRates.setLoanType("Home");
        baseInterestRates.setBaseInterestRate(8.5f);
        entityManager.persist(baseInterestRates);
        BaseInterestRates findBaseInterestRates = entityManager.find(BaseInterestRates.class,10);

        LoanPlans loanPlans = new LoanPlans();
        loanPlans.setPlanName("Home Loan");
        loanPlans.setPrincipleAmount(300000);
        loanPlans.setTenure(36);
        loanPlans.setInterestRate(8.5f);
        loanPlans.setInterestAmount(76500);
        loanPlans.setTotalPayable(376500);
        loanPlans.setEmi(10458.333f);
        loanPlans.setPlanValidity(LocalDate.now());
        loanPlans.setPlanAddedOn(LocalDate.now());
        loanPlans.setBaseInterestRates(findBaseInterestRates);
        entityManager.persist(loanPlans);
        Iterable<LoanPlans> iterableLoanPlans = loanPlansRepository.findAll();
        assertTrue(iterableLoanPlans.iterator().hasNext());

    }

    @Test
    void testFindAllNegative() {
        Iterable<LoanPlans> it = loanPlansRepository.findAll();
        assertTrue(!it.iterator().hasNext());

    }


    @Test
    void testFindByIdPositive() {
        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setId(20);
        baseInterestRates.setLoanType("Home");
        baseInterestRates.setBaseInterestRate(8.5f);
        entityManager.persist(baseInterestRates);
        BaseInterestRates findBaseInterestRates = entityManager.find(BaseInterestRates.class,20);

        LoanPlans loanPlans = new LoanPlans();
        loanPlans.setPlanName("Home Lone");
        loanPlans.setPrincipleAmount(300000);
        loanPlans.setTenure(10);
        loanPlans.setInterestRate(8.5f);
        loanPlans.setInterestAmount(50000);
        loanPlans.setTotalPayable(350000);
        loanPlans.setEmi(4000);
        loanPlans.setPlanValidity(LocalDate.of(2026, Month.APRIL,12));
        loanPlans.setPlanAddedOn(LocalDate.now());
        loanPlans.setBaseInterestRates(findBaseInterestRates);

        entityManager.persist(loanPlans);
        Optional<LoanPlans> optionalLoanPlans = loanPlansRepository.findById(loanPlans.getPlanId());
        assertTrue(optionalLoanPlans.isPresent());

    }

    @Test
    void testFindByIdNegative() {

        Optional<LoanPlans> lp = loanPlansRepository.findById(1);
        assertTrue(!lp.isPresent());
    }

    @Test
    void testSavePositive() {
        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setId(30);
        baseInterestRates.setLoanType("Home");
        baseInterestRates.setBaseInterestRate(8.5f);
        entityManager.persist(baseInterestRates);
        BaseInterestRates findBaseInterestRates = entityManager.find(BaseInterestRates.class,30);

        LoanPlans loanPlans = new LoanPlans();
        loanPlans.setPlanName("Home Lone");
        loanPlans.setPrincipleAmount(300000);
        loanPlans.setTenure(10);
        loanPlans.setInterestRate(8.5f);
        loanPlans.setInterestAmount(50000);
        loanPlans.setTotalPayable(350000);
        loanPlans.setEmi(4000);
        loanPlans.setPlanValidity(LocalDate.of(2026, Month.APRIL,12));
        loanPlans.setPlanAddedOn(LocalDate.now());
        loanPlans.setBaseInterestRates(findBaseInterestRates);

        loanPlansRepository.save(loanPlans);
        Optional<LoanPlans> lp = loanPlansRepository.findById(loanPlans.getPlanId());
        assertTrue(lp.isPresent());
    }

    @Test
    void testDeletePositive() {

        LoanPlans loanPlans = new LoanPlans();
        loanPlans.setPlanId(1);
        loanPlans.setPlanName("Home Lone");
        loanPlans.setPrincipleAmount(300000);
        loanPlans.setTenure(10);
        loanPlans.setInterestRate(8.5f);
        loanPlans.setInterestAmount(50000);
        loanPlans.setTotalPayable(350000);
        loanPlans.setEmi(4000);
        loanPlans.setPlanValidity(LocalDate.of(2026, Month.APRIL,12));
        loanPlans.setPlanAddedOn(LocalDate.now());
        loanPlansRepository.delete(loanPlans);
        Optional<LoanPlans> lp = loanPlansRepository.findById(1);
        assertTrue(!lp.isPresent());

    }

}
