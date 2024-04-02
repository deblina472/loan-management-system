package com.cognizant.services;

import com.cognizant.dto.BaseInterestRatesDTO;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.repositories.BaseInterestRatesRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class BaseInterestRatesServiceImpl implements BaseInterestRatesService {
    @Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;


    @Override
    public List<BaseInterestRatesDTO> getInterestRateList() {
        List<BaseInterestRatesDTO> baseInterestRatesDTOList = new ArrayList<>();
        Iterable<BaseInterestRates>  iterableBaseInterestRates=baseInterestRatesRepository.findAll();
        Iterator<BaseInterestRates> iteratorBaseInterestRates=iterableBaseInterestRates.iterator();
        while(iteratorBaseInterestRates.hasNext()){
            BaseInterestRates baseInterestRates=iteratorBaseInterestRates.next();
            BaseInterestRatesDTO baseInterestRatesDTO = new BaseInterestRatesDTO();

            baseInterestRatesDTO.setId(baseInterestRates.getId());
            baseInterestRatesDTO.setLoanType(baseInterestRates.getLoanType());
            baseInterestRatesDTO.setBaseInterestRate(baseInterestRates.getBaseInterestRate());
            baseInterestRatesDTOList.add(baseInterestRatesDTO);
        }
        return baseInterestRatesDTOList;
    }

}
