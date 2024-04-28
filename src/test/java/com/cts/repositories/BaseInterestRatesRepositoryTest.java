package com.cognizant.repository;

import com.cognizant.LoanPlansModuleApplication;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.repositories.BaseInterestRatesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ContextConfiguration(classes = LoanPlansModuleApplication.class)
class BaseInterestRatesRepositoryTest {

    @Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testFindAllPositive() {
        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setId(10);
        baseInterestRates.setLoanType("Home");
        baseInterestRates.setBaseInterestRate(8.5f);
        entityManager.persist(baseInterestRates);
        Iterable<BaseInterestRates> it = baseInterestRatesRepository.findAll();
        assertTrue(it.iterator().hasNext());

    }

    @Test
    void testFindAllNegative() {
        baseInterestRatesRepository.deleteAll();
        Iterable<BaseInterestRates> it = baseInterestRatesRepository.findAll();
        assertTrue(!it.iterator().hasNext());

    }


    @Test
    void testFindByIdPositive() {

        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setId(1);
        baseInterestRates.setLoanType("Home");
        baseInterestRates.setBaseInterestRate(8.5f);
        entityManager.persist(baseInterestRates);
        Optional<BaseInterestRates> bir = baseInterestRatesRepository.findById(1);
        assertTrue(bir.isPresent());
    }

    @Test
    void testFindByIdNegative() {

        Optional<BaseInterestRates> bir = baseInterestRatesRepository.findById(89);
        assertTrue(!bir.isPresent());
    }

    @Test
    void testSavePositive() {

        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setId(1);
        baseInterestRates.setLoanType("Home");
        baseInterestRates.setBaseInterestRate(8.5f);
        baseInterestRatesRepository.save(baseInterestRates);
        Optional<BaseInterestRates> bir = baseInterestRatesRepository.findById(1);
        assertTrue(bir.isPresent());
    }

    @Test
    void testDeletePositive() {

        BaseInterestRates baseInterestRates = new BaseInterestRates();
        baseInterestRates.setId(1);
        baseInterestRates.setLoanType("Home");
        baseInterestRates.setBaseInterestRate(8.5f);
        entityManager.persist(baseInterestRates);
        baseInterestRatesRepository.delete(baseInterestRates);
        Optional<BaseInterestRates> bir = baseInterestRatesRepository.findById(1);
        assertTrue(!bir.isPresent());

    }


}
