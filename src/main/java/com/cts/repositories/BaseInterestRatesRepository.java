package com.cognizant.repositories;

import com.cognizant.entities.BaseInterestRates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseInterestRatesRepository extends CrudRepository<BaseInterestRates, Integer> {
    BaseInterestRates findByLoanType(String loanType);
}
