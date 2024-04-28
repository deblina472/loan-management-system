package com.cognizant.repository;

import com.cognizant.LoanPlansModuleApplication;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.entities.LoanPlans;
import com.cognizant.entities.LoanPlansHistory;
import com.cognizant.repositories.LoanPlansHistoryRepository;
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
class LoanPlansHistoryRepositoryTest {
    @Autowired
    private LoanPlansHistoryRepository loanPlansHistoryRepository;
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

        if(optionalLoanPlans.isPresent()){
            LoanPlansHistory loanPlansHistory = new LoanPlansHistory();
                loanPlansHistory.setUpdatedDate(LocalDate.now());
                loanPlansHistory.setLoanPlans(optionalLoanPlans.get());
            entityManager.persist(loanPlansHistory);
        }

        Iterable<LoanPlansHistory> iterableLoanPlanHistory = loanPlansHistoryRepository.findAll();
        assertTrue(iterableLoanPlanHistory.iterator().hasNext());

    }

    @Test
    void testFindAllNegative() {
        Iterable<LoanPlansHistory> iterableLoanPlanHistory = loanPlansHistoryRepository.findAll();
        assertTrue(!iterableLoanPlanHistory.iterator().hasNext());

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
        LoanPlansHistory loanPlansHistory = new LoanPlansHistory();

        if(optionalLoanPlans.isPresent()) {

            loanPlansHistory.setUpdatedDate(LocalDate.now());
            loanPlansHistory.setLoanPlans(optionalLoanPlans.get());

            entityManager.persist(loanPlansHistory);
        }
        Optional<LoanPlansHistory> optionalLoanPlansHistory = loanPlansHistoryRepository.findById(loanPlansHistory.getId());
        assertTrue(optionalLoanPlansHistory.isPresent());

    }

    @Test
    void testFindByIdNegative() {

        Optional<LoanPlansHistory> lph = loanPlansHistoryRepository.findById(1);
        assertTrue(!lph.isPresent());
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
        entityManager.persist(loanPlans);
        Optional<LoanPlans> optionalLoanPlans = loanPlansRepository.findById(loanPlans.getPlanId());

        LoanPlansHistory loanPlansHistory = new LoanPlansHistory();
        if(optionalLoanPlans.isPresent()) {
            loanPlansHistory.setUpdatedDate(LocalDate.now());
            loanPlansHistory.setLoanPlans(optionalLoanPlans.get());

            loanPlansHistoryRepository.save(loanPlansHistory);
        }
        Optional<LoanPlansHistory> optionalLoanPlansHistory = loanPlansHistoryRepository.findById(loanPlansHistory.getId());
        assertTrue(optionalLoanPlansHistory.isPresent());
    }

    @Test
    void testDeletePositive() {

        LoanPlansHistory loanPlansHistory = new LoanPlansHistory();
        loanPlansHistory.setId(1);
        loanPlansHistory.setUpdatedDate(LocalDate.now());
        loanPlansHistoryRepository.delete(loanPlansHistory);
        Optional<LoanPlansHistory> lph = loanPlansHistoryRepository.findById(1);
        assertTrue(!lph.isPresent());

    }

}
