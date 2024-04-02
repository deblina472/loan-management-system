package com.cognizant.services;

import com.cognizant.dto.BaseInterestRatesDTO;
import com.cognizant.dto.LoanPlansDTO;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.entities.LoanPlans;
import com.cognizant.repositories.BaseInterestRatesRepository;
import com.cognizant.repositories.LoanPlansRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Data
@Service
public class LoanPlansServiceImpl implements LoanPlansService {
    @Autowired
    private LoanPlansRepository loanPlansRepository;
    @Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;
    private BaseInterestRatesDTO baseInterestRatesDTO;

/* Add new loan plan */
    @Override
    public String addNewPlan(LoanPlansDTO loanPlansDTO) {

        LoanPlans loanPlan = new LoanPlans();
        loanPlan.setPlanName(loanPlansDTO.getPlanName());
        loanPlan.setPrincipleAmount(loanPlansDTO.getPrincipleAmount());
        loanPlan.setTenure(loanPlansDTO.getTenure());
        loanPlan.setInterestRate(loanPlansDTO.getInterestRate());
        calculateInterestAmountAndTotalPayable(loanPlansDTO.getBaseInterestRates().getLoanType(),loanPlansDTO.getPrincipleAmount(),loanPlansDTO.getTenure(),loanPlan);
        loanPlan.setPlanValidity(loanPlansDTO.getPlanValidity());
        loanPlan.setPlanAddedOn(loanPlansDTO.getPlanAddedOn());
//        BaseInterestRates baseInterestRates = new BaseInterestRates();
//        baseInterestRates.setId(loanPlansDTO.getBaseInterestRates().getId());
//        loanPlan.setBaseInterestRates(baseInterestRates);

        LoanPlans loanPlans = loanPlansRepository.save(loanPlan);
        if (loanPlans != null)
            return "success";
        else
            return "fail";
    }

    public void calculateInterestAmountAndTotalPayable(String loanType, int principleAmount, int tenure,LoanPlans loanPlans) {
        double interestRate ;
        double totalPayableAmount = 0.0;
        double interestAmount = 0.0;
        double emi = 0.0;
        double year = tenure / 12;
        if (principleAmount <= 500000 && year <= 5) {
            switch (loanType) {
                case "Personal" -> {
                    interestRate = 8.5;
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                    break;
                }
                case "Home" -> {
                    interestRate = 10;
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                    break;
                }
                case "Vehicle" -> {
                    interestRate = 7.5F;
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                    break;
                }
                case "Medical" -> {
                    interestRate = 8.0F;
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                    break;
                }
                default -> {
                    break;
                }
            }
        }
        else if (principleAmount > 500000 && principleAmount <= 10000000 && year > 5 && year <= 30) {
            switch (loanType) {
                case "Personal" -> {
                    interestRate = 8.5 + (0.2 * year);
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                    break;
                }
                case "Home" -> {
                    interestRate = 10 + (0.3 * year);
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                    break;
                }
                case "Vehicle" -> {
                    interestRate = 7.5 + (0.25 * year);
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                    break;
                }
                case "Medical" -> {
                    interestRate = 8 + (0.25 * year);
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                    break;
                }
                default -> {
                    break;
                }
            }
        }

        loanPlans.setInterestAmount((int) interestAmount);
        loanPlans.setTotalPayable((int) totalPayableAmount);
        loanPlans.setEmi((int) emi);
    }

    public double calculateTotalPayable(int principleAmount,double year,double interestRate){
        return (principleAmount * (interestRate / 100) * year) + principleAmount;
    }

/**
 *  Fetch list of loan plans
 */
    public List<LoanPlansDTO> fetchLoanPlans() {
        Iterable<LoanPlans> loanPlansIterable = loanPlansRepository.findAll();
        Iterator<LoanPlans> loanPlansIterator = loanPlansIterable.iterator();
        List<LoanPlansDTO> loanPlans = new LoanPlansServiceImpl().convertToDTOList(loanPlansIterator);

        return loanPlans;
    }
    private List<LoanPlansDTO> convertToDTOList(Iterator<LoanPlans> loanPlansIterator) {
        List<LoanPlansDTO> loanPlansDTOList = new ArrayList<>();
        while (loanPlansIterator.hasNext()){
            LoanPlans loanPlans = loanPlansIterator.next();
            LoanPlansDTO loanPlansDTO = new LoanPlansDTO();

            loanPlansDTO.setPlanId(loanPlans.getPlanId());
            loanPlansDTO.setPlanName(loanPlans.getPlanName());
            loanPlansDTO.setPrincipleAmount(loanPlans.getPrincipleAmount());
            loanPlansDTO.setTenure(loanPlans.getTenure());
            loanPlansDTO.setInterestRate(loanPlans.getInterestRate());
            loanPlansDTO.setInterestAmount(loanPlans.getInterestAmount());
            loanPlansDTO.setTotalPayable(loanPlans.getTotalPayable());
            loanPlansDTO.setEmi(loanPlans.getEmi());
            loanPlansDTO.setPlanValidity(loanPlans.getPlanValidity());
            loanPlansDTO.setPlanAddedOn(loanPlans.getPlanAddedOn());
            loanPlansDTO.setBaseInterestRates(loanPlans.getBaseInterestRates());

            loanPlansDTOList.add(loanPlansDTO);

        }
        return loanPlansDTOList;
    }

//Fetch loan plan by Id
    @Override
    public LoanPlansDTO fetchLoanPlanById(int planId) {
        Optional<LoanPlans> loanPlans = loanPlansRepository.findById(planId);

        LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
        loanPlans.ifPresent(fetchLoanPlans -> {

            loanPlansDTO.setPlanName(fetchLoanPlans.getPlanName());
            loanPlansDTO.setPrincipleAmount(fetchLoanPlans.getPrincipleAmount());
            loanPlansDTO.setTenure(fetchLoanPlans.getTenure());
            loanPlansDTO.setInterestRate(fetchLoanPlans.getInterestRate());
            loanPlansDTO.setInterestAmount(fetchLoanPlans.getInterestAmount());
            loanPlansDTO.setTotalPayable(fetchLoanPlans.getTotalPayable());
            loanPlansDTO.setEmi(fetchLoanPlans.getEmi());
            loanPlansDTO.setPlanValidity(fetchLoanPlans.getPlanValidity());
            loanPlansDTO.setPlanAddedOn(fetchLoanPlans.getPlanAddedOn());
            loanPlansDTO.setBaseInterestRates(fetchLoanPlans.getBaseInterestRates());
        });
        return loanPlansDTO;
    }

//Update loan plan by Id
@Override
public String updateLoanPlan(int planId, LoanPlansDTO loanPlansDTO) {
    Optional<LoanPlans> optionalLoanPlans = loanPlansRepository.findById(loanPlansDTO.getPlanId());
    if (optionalLoanPlans.isPresent()) {
        LoanPlans existingLoanPlan = optionalLoanPlans.get();

        existingLoanPlan.setPlanName(loanPlansDTO.getPlanName());
        existingLoanPlan.setPlanValidity(loanPlansDTO.getPlanValidity());

        loanPlansRepository.save(existingLoanPlan);
        return "success";
    }
    return "fail";
}



}

