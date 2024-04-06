package com.cognizant.services.classes;

import com.cognizant.dto.BaseInterestRatesDTO;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.repositories.BaseInterestRatesRepository;
import com.cognizant.services.interfaces.BaseInterestRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author Deblina Das
 * This is a service class where business logic for getting list of interest rates
 */
@Service
public class BaseInterestRatesServiceImpl implements BaseInterestRatesService {
    @Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;

    /**
     * get list of interest rates
     * @return
     */
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
