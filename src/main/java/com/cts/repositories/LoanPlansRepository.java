package com.cognizant.repositories;

import com.cognizant.entities.LoanPlans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPlansRepository extends CrudRepository<LoanPlans, Integer> {

}
