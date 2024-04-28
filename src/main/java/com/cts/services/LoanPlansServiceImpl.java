package com.cognizant.services.classes;

import com.cognizant.dto.LoanPlansDTO;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.entities.LoanPlans;
import com.cognizant.repositories.BaseInterestRatesRepository;
import com.cognizant.repositories.LoanPlansRepository;
import com.cognizant.services.interfaces.LoanPlansService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author Deblina Das
 * This is a service class containing business logics
 * for several operations performed by bank manager and customers
 */
@Data
@Service
@Slf4j
public class LoanPlansServiceImpl implements LoanPlansService {
    @Autowired
    private LoanPlansRepository loanPlansRepository;
    @Autowired
    private BaseInterestRatesRepository baseInterestRatesRepository;

    /**
     * This method will create new loan plan
     * @param loanPlansDTO - This method takes object of LoanPlansDTO as parameter
     * @return - This method returns LoanPlansDTO
     */
    @Override
    public LoanPlansDTO addNewPlan(LoanPlansDTO loanPlansDTO) {
        BaseInterestRates baseInterestRates=new BaseInterestRates();
        baseInterestRates.setId(loanPlansDTO.getLoanTypeId());
        baseInterestRates.setLoanType(loanPlansDTO.getLoanType());
        baseInterestRates.setBaseInterestRate(loanPlansDTO.getBaseInterestRate());

        LoanPlans loanPlan = new LoanPlans();
        loanPlan.setPlanName(loanPlansDTO.getPlanName());
        loanPlan.setPrincipleAmount(loanPlansDTO.getPrincipleAmount());
        loanPlan.setTenure(loanPlansDTO.getTenure());
        LoanPlansServiceImpl loanPlansServiceImpl=new LoanPlansServiceImpl();
        loanPlan.setPlanValidity(loanPlansDTO.getPlanValidity());
        loanPlan.setPlanAddedOn(LocalDate.now());
        loanPlansServiceImpl.calculateInterestRateAndInterestAmountAndEMI(
                loanPlansDTO.getLoanType(),
                loanPlansDTO.getPrincipleAmount(),
                loanPlansDTO.getTenure(),
                loanPlan
        );

        Optional<BaseInterestRates> optionalOfBaseInterestRates = baseInterestRatesRepository.findById(loanPlansDTO.getLoanTypeId());
        if(optionalOfBaseInterestRates.isPresent()){
            loanPlan.setBaseInterestRates(optionalOfBaseInterestRates.get());
        }
        LoanPlans loanPlans = loanPlansRepository.save(loanPlan);
        if(loanPlans.getPlanId()!=0){
            loanPlansDTO.setPlanId(loanPlans.getPlanId());
        }

        return loanPlansDTO;

    }

    /**
     * This method calculates interest rate, interest amount and EMI and save the data into entity
     * @param loanType - It takes loanType as first parameter
     * @param principleAmount - It takes principleAmount as second parameter
     * @param tenure - It takes tenure as third parameter
     * @param loanPlans - It takes object of LoanPlans as fourth parameter
     */
    public void calculateInterestRateAndInterestAmountAndEMI(String loanType, int principleAmount, int tenure, LoanPlans loanPlans) {
        double interestRate =0.0;
        double totalPayableAmount = 0.0;
        double interestAmount = 0.0;
        double emi = 0.0;
        double year = tenure / 12;
        if (principleAmount <= 500000 && year <= 5) {
            switch (loanType) {
                case "Home" -> {
                    interestRate = 8.5;
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                }
                case "Personal" -> {
                    interestRate = 10;
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                }
                case "Medical" -> {
                    interestRate = 7.5F;
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                }
                case "Vehicle" -> {
                    interestRate = 8.0F;
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                }
                default -> {
                    break;
                }
            }
        }
        if (principleAmount > 500000 && principleAmount <= 10000000 && year > 5 && year <= 30) {
            switch (loanType) {
                case "Home" -> {
                    interestRate = 8.5 + (0.2 * year);
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                }
                case "Personal" -> {
                    interestRate = 10 + (0.3 * year);
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                }
                case "Medical" -> {
                    interestRate = 7.5 + (0.25 * year);
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                }
                case "Vehicle" -> {
                    interestRate = 8 + (0.25 * year);
                    totalPayableAmount = calculateTotalPayable(principleAmount, year, interestRate);
                    interestAmount = totalPayableAmount - principleAmount;
                    emi = totalPayableAmount / tenure;
                }
                default -> {
                    break;
                }
            }
        }
        loanPlans.setInterestRate((float) interestRate);
        loanPlans.setInterestAmount((int) interestAmount);
        loanPlans.setTotalPayable((int) totalPayableAmount);
        loanPlans.setEmi((float) emi);
    }

    /**
     * This method calculates total payable amount
     * @param principleAmount - It takes principleAmount as first parameter
     * @param year - It takes year as second parameter
     * @param interestRate - It takes interestRate as third parameter
     * @return - It returns total payable amount
     */
    public double calculateTotalPayable(int principleAmount,double year,double interestRate){
        return (principleAmount * (interestRate / 100) * year) + principleAmount;
    }


    /**
     * This method fetches list of loan plans
     * @return - This method returns list of LoanPlansDTO
     */

    public List<LoanPlansDTO> fetchLoanPlans() {
        Iterable<LoanPlans> loanPlansIterable = loanPlansRepository.findAll();
        Iterator<LoanPlans> loanPlansIterator = loanPlansIterable.iterator();
        List<LoanPlansDTO> loanPlans = new LoanPlansServiceImpl().convertToDTOList(loanPlansIterator);

        return loanPlans;
    }

    /**
     * This method converts iterator of entity into dto list
     * @param loanPlansIterator - This method takes iterator of LoanPlans as parameter
     * @return - This method returns list of LoanPlansDTO
     */
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
            loanPlansDTO.setLoanTypeId(loanPlans.getBaseInterestRates().getId());
            loanPlansDTO.setLoanType(loanPlans.getBaseInterestRates().getLoanType());
            loanPlansDTO.setBaseInterestRate(loanPlans.getBaseInterestRates().getBaseInterestRate());


            loanPlansDTOList.add(loanPlansDTO);
        }
        return loanPlansDTOList;
    }


    /**
     * This method gets loan plan by id
     * @param planId - It is the id of loan plan taken as first parameter
     * @return - It will return dto of loan plan
     */
    @Override
    public LoanPlansDTO fetchLoanPlanById(int planId) {
        Optional<LoanPlans> loanPlans = loanPlansRepository.findById(planId);

        LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
        loanPlans.ifPresent(fetchLoanPlans -> {
            loanPlansDTO.setPlanId(fetchLoanPlans.getPlanId());
            loanPlansDTO.setPlanName(fetchLoanPlans.getPlanName());
            loanPlansDTO.setPrincipleAmount(fetchLoanPlans.getPrincipleAmount());
            loanPlansDTO.setTenure(fetchLoanPlans.getTenure());
            loanPlansDTO.setInterestRate(fetchLoanPlans.getInterestRate());
            loanPlansDTO.setInterestAmount(fetchLoanPlans.getInterestAmount());
            loanPlansDTO.setTotalPayable(fetchLoanPlans.getTotalPayable());
            loanPlansDTO.setEmi(fetchLoanPlans.getEmi());
            loanPlansDTO.setPlanValidity(fetchLoanPlans.getPlanValidity());
            loanPlansDTO.setPlanAddedOn(fetchLoanPlans.getPlanAddedOn());
            loanPlansDTO.setLoanTypeId(fetchLoanPlans.getBaseInterestRates().getId());
            loanPlansDTO.setLoanType(fetchLoanPlans.getBaseInterestRates().getLoanType());
            loanPlansDTO.setBaseInterestRate(fetchLoanPlans.getBaseInterestRates().getBaseInterestRate());
        });
        return loanPlansDTO;
    }


    /**
     * This method updates loan plan by id
     * @param planId - It takes id of a loan plan as first parameter
     * @param loanPlansDTO - It takes object of LoanPlansDTO as second parameter
     * @return - It returns status of update
     */
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

