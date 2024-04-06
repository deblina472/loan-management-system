package com.cognizant.repository;

import com.cognizant.LoanPlansModuleApplication;
import com.cognizant.entities.LoanPlans;
import com.cognizant.repositories.LoanPlansRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = LoanPlansModuleApplication.class)
public class LoanPlansRepositoryTest {
    @Autowired
    private LoanPlansRepository loanPlansRepository;
    @Autowired
    private TestEntityManager entityManager;

//    @Test
//    public void testFindAllPositive() {
//        BaseInterestRates baseInterestRates = new BaseInterestRates();
//        baseInterestRates.setId(1);
//        entityManager.persist(baseInterestRates);
//        BaseInterestRates findBaseInterestRates = entityManager.find(BaseInterestRates.class,1);
//
//        LoanPlans loanPlans = new LoanPlans();
//        loanPlans.setBaseInterestRates(findBaseInterestRates);
//        loanPlans.setPlanId(1);
//        loanPlans.setPlanName("Home");
//        loanPlans.setPrincipleAmount(300000);
//        loanPlans.setTenure(10);
//        loanPlans.setInterestRate(8.5f);
//        loanPlans.setInterestAmount(50000);
//        loanPlans.setTotalPayable(350000);
//        loanPlans.setEmi(4000);
//        loanPlans.setPlanValidity(LocalDate.now());//"2034-06-10T00:00:00Z"
//        loanPlans.setPlanAddedOn(LocalDate.now());
//        loanPlans.setBaseInterestRates(findbaseInterestRates);
//        entityManager.persist(loanPlans);
//        Iterable<LoanPlans> it = loanPlansRepository.findAll();
//        assertTrue(it.iterator().hasNext());
//
//    }

    @Test
    public void testFindAllNegative() {
        Iterable<LoanPlans> it = loanPlansRepository.findAll();
        assertTrue(!it.iterator().hasNext());

    }


//    @Test
//    public void testFindByIdPositive() {
//        LoanPlans loanPlans = new LoanPlans();
//        loanPlans.setPlanId(1);
//        loanPlans.setPlanName("Home Lone");
//        loanPlans.setPrincipleAmount(300000);
//        loanPlans.setTenure(10);
//        loanPlans.setInterestRate(8.5f);
//        loanPlans.setInterestAmount(50000);
//        loanPlans.setTotalPayable(350000);
//        loanPlans.setEmi(4000);
//        loanPlans.setPlanValidity(LocalDate.of(2026, Month.APRIL,12));//"2034-06-10T00:00:00Z"
//        loanPlans.setPlanAddedOn(LocalDate.now());
//        entityManager.persist(loanPlans);
//        Optional<LoanPlans> lp = loanPlansRepository.findById(1);
//        assertTrue(lp.isPresent());
//
//    }

    @Test
    public void testFindByIdNegative() {

        Optional<LoanPlans> lp = loanPlansRepository.findById(1);
        assertTrue(!lp.isPresent());
    }

    @Test
    public void testSavePositive() {
        LoanPlans loanPlans = new LoanPlans();
        loanPlans.setPlanId(1);
        loanPlans.setPlanName("Home Lone");
        loanPlans.setPrincipleAmount(300000);
        loanPlans.setTenure(10);
        loanPlans.setInterestRate(8.5f);
        loanPlans.setInterestAmount(50000);
        loanPlans.setTotalPayable(350000);
        loanPlans.setEmi(4000);
        loanPlans.setPlanValidity(LocalDate.now());//"2034-06-10T00:00:00Z"
        loanPlans.setPlanAddedOn(LocalDate.now());
        loanPlansRepository.save(loanPlans);
        Optional<LoanPlans> lp = loanPlansRepository.findById(1);
        assertTrue(lp.isPresent());
    }

    @Test
    public void testDeletePositive() {

        LoanPlans loanPlans = new LoanPlans();
        loanPlans.setPlanId(1);
        loanPlans.setPlanName("Home Lone");
        loanPlans.setPrincipleAmount(300000);
        loanPlans.setTenure(10);
        loanPlans.setInterestRate(8.5f);
        loanPlans.setInterestAmount(50000);
        loanPlans.setTotalPayable(350000);
        loanPlans.setEmi(4000);
        loanPlans.setPlanValidity(LocalDate.now());//"2034-06-10T00:00:00Z"
        loanPlans.setPlanAddedOn(LocalDate.now());
        loanPlansRepository.delete(loanPlans);
        Optional<LoanPlans> lp = loanPlansRepository.findById(1);
        assertTrue(!lp.isPresent());

    }

}
