package com.cognizant.repository;

import com.cognizant.LoanPlansModuleApplication;
import com.cognizant.entities.LoanPlansHistory;
import com.cognizant.repositories.LoanPlansHistoryRepository;
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
public class LoanPlansHistoryRepositoryTest {
    @Autowired
    private LoanPlansHistoryRepository loanPlansHistoryRepository;
    @Autowired
    private TestEntityManager entityManager;

//    @Test
//    public void testFindAllPositive() {
//
//        LoanPlansHistory loanPlansHistory = new LoanPlansHistory();
//        loanPlansHistory.setId(1);
//        loanPlansHistory.setUpdatedDate(LocalDate.now());
//        entityManager.persist(loanPlansHistory);
//        Iterable<LoanPlansHistory> it = loanPlansHistoryRepository.findAll();
//        assertTrue(it.iterator().hasNext());
//
//    }

    @Test
    public void testFindAllNegative() {
        Iterable<LoanPlansHistory> it = loanPlansHistoryRepository.findAll();
        assertTrue(!it.iterator().hasNext());

    }


//    @Test
//    public void testFindByIdPositive() {
//
//        LoanPlansHistory loanPlansHistory = new LoanPlansHistory();
//        loanPlansHistory.setId(1);
//        loanPlansHistory.setUpdatedDate(LocalDate.now());
//        entityManager.persist(loanPlansHistory);
//        Optional<LoanPlansHistory> lph = loanPlansHistoryRepository.findById(1);
//        assertTrue(lph.isPresent());
//
//    }

    @Test
    public void testFindByIdNegative() {

        Optional<LoanPlansHistory> lph = loanPlansHistoryRepository.findById(1);
        assertTrue(!lph.isPresent());
    }

    @Test
    public void testSavePositive() {

        LoanPlansHistory loanPlansHistory = new LoanPlansHistory();
        loanPlansHistory.setId(1);
        loanPlansHistory.setUpdatedDate(LocalDate.now());
        loanPlansHistoryRepository.save(loanPlansHistory);
        Optional<LoanPlansHistory> lph = loanPlansHistoryRepository.findById(1);
        assertTrue(lph.isPresent());
    }

    @Test
    public void testDeletePositive() {

        LoanPlansHistory loanPlansHistory = new LoanPlansHistory();
        loanPlansHistory.setId(1);
        loanPlansHistory.setUpdatedDate(LocalDate.now());
        loanPlansHistoryRepository.delete(loanPlansHistory);
        Optional<LoanPlansHistory> lph = loanPlansHistoryRepository.findById(1);
        assertTrue(!lph.isPresent());

    }

}
