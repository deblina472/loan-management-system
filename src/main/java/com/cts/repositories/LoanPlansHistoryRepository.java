package com.cognizant.repositories;

import com.cognizant.entities.LoanPlansHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPlansHistoryRepository extends CrudRepository<LoanPlansHistory, Integer> {
    //Iterable<LoanPlansHistory>
}
