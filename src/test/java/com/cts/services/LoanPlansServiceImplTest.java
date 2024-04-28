package com.cognizant.services;

import com.cognizant.dto.BaseInterestRatesDTO;
import com.cognizant.dto.LoanPlansDTO;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.entities.LoanPlans;
import com.cognizant.repositories.BaseInterestRatesRepository;
import com.cognizant.repositories.LoanPlansRepository;
import com.cognizant.services.classes.LoanPlansServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoanPlansServiceImplTest {
    @Mock
    private LoanPlansRepository loanPlansRepository;
    @Mock
    private BaseInterestRatesRepository baseInterestRatesRepository;
    @Mock
    private LoanPlansServiceImpl mockOfLoanPlansServiceImpl;
    @InjectMocks
    private LoanPlansServiceImpl loanPlansServiceImpl;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception{}



    @Test
    void testAddNewPlan_Success() {
        try {
            BaseInterestRates baseInterestRatesmock =mock(BaseInterestRates.class);
            BaseInterestRates baseInterestRates = new BaseInterestRates();

            LoanPlans loanPlansMock = mock(LoanPlans.class);
            LoanPlans loanPlans = new LoanPlans();
            LoanPlansDTO loanPlansDTO = new LoanPlansDTO();

            loanPlansDTO.setLoanTypeId(1);
            loanPlansDTO.setLoanType("Home");
            loanPlansDTO.setBaseInterestRate(8.5f);

            loanPlansDTO.setPlanId(10);
            loanPlansDTO.setPlanName("Home");
            loanPlansDTO.setPrincipleAmount(1000000);
            loanPlansDTO.setTenure(90);
            loanPlansDTO.setPlanValidity(LocalDate.of(2026, Month.APRIL,12));
            loanPlansDTO.setPlanAddedOn(LocalDate.now());
            loanPlansDTO.setLoanTypeId(1);
            loanPlansDTO.setLoanType("Home");
            loanPlansDTO.setBaseInterestRate(8.5f);

            baseInterestRates.setId(loanPlansDTO.getLoanTypeId());
            baseInterestRates.setLoanType(loanPlansDTO.getLoanType());
            baseInterestRates.setBaseInterestRate(loanPlansDTO.getBaseInterestRate());
            when(baseInterestRatesRepository.save(any())).thenReturn(baseInterestRatesmock);


            loanPlans.setPlanId(10);
            loanPlans.setPlanName("Home");
            loanPlans.setPrincipleAmount(1000000);
            loanPlans.setTenure(90);
            loanPlans.setPlanValidity(LocalDate.of(2026, Month.APRIL,12));
            loanPlans.setPlanAddedOn(LocalDate.now());
            loanPlans.setBaseInterestRates(baseInterestRatesmock);

            mockOfLoanPlansServiceImpl.calculateInterestRateAndInterestAmountAndEMI("Home",1000000,90,loanPlans);

           verify(mockOfLoanPlansServiceImpl,times(1))
             .calculateInterestRateAndInterestAmountAndEMI("Home",1000000,90,loanPlans);

           when(loanPlansRepository.save(any())).thenReturn(loanPlansMock);
            loanPlansDTO.setPlanId(loanPlans.getPlanId());

            loanPlansServiceImpl.addNewPlan(loanPlansDTO);
            assertEquals(loanPlans.getPlanId(),loanPlansDTO.getPlanId());
        }
        catch(Exception e){
            assertTrue(false);
        }
    }
    @Test
    void testAddNewPlan_Failure() {
        try {
            LoanPlansDTO loanPlansDTO = new LoanPlansDTO();

            when(loanPlansRepository.save(Mockito.any())).thenReturn(null);
            loanPlansServiceImpl.addNewPlan(loanPlansDTO);
            assertTrue(loanPlansDTO == null);
        }
        catch(Exception e){
            assertTrue(true);
        }
    }
    @Test
    void testAddNewPlan_Exception() {
        try {
            LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
            loanPlansDTO.setPlanId(0);
            loanPlansDTO.setPlanName("Home");
            loanPlansDTO.setPrincipleAmount(1000000);
            loanPlansDTO.setTenure(90);
            loanPlansDTO.setInterestRate(8.5F);
            loanPlansDTO.setInterestAmount(85000);
            loanPlansDTO.setTotalPayable(1086000);
            loanPlansDTO.setEmi(950.0F);
            loanPlansDTO.setPlanValidity(LocalDate.of(2026, Month.APRIL, 12));
            loanPlansDTO.setPlanAddedOn(LocalDate.now());

            when(loanPlansRepository.save(Mockito.any())).thenThrow(SQLException.class);
            loanPlansServiceImpl.addNewPlan(loanPlansDTO);
            assertTrue(false);
        }
        catch (Exception e){
            assertTrue(true);
        }
    }

    @Test
    void testFetchLoanPlans_Positive_WhenDTOIsFound() {
        try{

            BaseInterestRates baseInterestRates =mock(BaseInterestRates.class);
            baseInterestRates.setId(1);
            baseInterestRates.setLoanType("Home");
            baseInterestRates.setBaseInterestRate(8.5f);

            Iterable<LoanPlans> iterableMock = mock(Iterable.class);
            when(loanPlansRepository.findAll()).thenReturn(iterableMock);
            Iterator<LoanPlans> iteratorMock = mock(Iterator.class);
            when(iterableMock.iterator()).thenReturn(iteratorMock);
            when(iteratorMock.hasNext()).thenReturn(true).thenReturn(false);
            LoanPlans loanPlansMock = mock(LoanPlans.class);
            when(iteratorMock.next()).thenReturn(loanPlansMock);
            when(loanPlansMock.getPlanId()).thenReturn(1);
            when(loanPlansMock.getPlanName()).thenReturn("Home");
            when(loanPlansMock.getPrincipleAmount()).thenReturn(1000000);
            when(loanPlansMock.getTenure()).thenReturn(90);
            when(loanPlansMock.getInterestRate()).thenReturn(8.5F);
            when(loanPlansMock.getInterestAmount()).thenReturn(85000);
            when(loanPlansMock.getTotalPayable()).thenReturn(1085000);
            when(loanPlansMock.getEmi()).thenReturn(950.0F);
            when(loanPlansMock.getPlanValidity()).thenReturn(LocalDate.now());
            when(loanPlansMock.getBaseInterestRates()).thenReturn(baseInterestRates);

            List<LoanPlansDTO> loanPlansDTOList = loanPlansServiceImpl.fetchLoanPlans();
            assertTrue(loanPlansDTOList.size()==1);
        }
        catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
    }
    @Test
    void testFetchLoanPlans_Positive_WhenMoreThanOneDTOIsFound() {
        try{
            BaseInterestRates baseInterestRates =mock(BaseInterestRates.class);
            baseInterestRates.setId(1);
            baseInterestRates.setLoanType("Home");
            baseInterestRates.setBaseInterestRate(8.5f);

            Iterable<LoanPlans> iterableMock = mock(Iterable.class);
            when(loanPlansRepository.findAll()).thenReturn(iterableMock);
            Iterator<LoanPlans> iteratorMock = mock(Iterator.class);
            when(iterableMock.iterator()).thenReturn(iteratorMock);
            when(iteratorMock.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
            LoanPlans loanPlansMock = mock(LoanPlans.class);
            when(iteratorMock.next()).thenReturn(loanPlansMock);
            when(loanPlansMock.getPlanId()).thenReturn(1);
            when(loanPlansMock.getPlanName()).thenReturn("Home");
            when(loanPlansMock.getPrincipleAmount()).thenReturn(1000000);
            when(loanPlansMock.getTenure()).thenReturn(90);
            when(loanPlansMock.getInterestRate()).thenReturn(8.5F);
            when(loanPlansMock.getInterestAmount()).thenReturn(85000);
            when(loanPlansMock.getTotalPayable()).thenReturn(1085000);
            when(loanPlansMock.getEmi()).thenReturn(950.0F);
            when(loanPlansMock.getPlanValidity()).thenReturn(LocalDate.now());
            when(loanPlansMock.getBaseInterestRates()).thenReturn(baseInterestRates);


            List<LoanPlansDTO> loanPlansDTOList = loanPlansServiceImpl.fetchLoanPlans();
            assertTrue(loanPlansDTOList.size() > 1);
        }
        catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
    }
    @Test
    void testFetchLoanPlans_Negative() {
        try{
            Iterable<LoanPlans> iterableMock = mock(Iterable.class);
            when(loanPlansRepository.findAll()).thenReturn(iterableMock);
            Iterator<LoanPlans> iteratorMock = mock(Iterator.class);
            when(iterableMock.iterator()).thenReturn(iteratorMock);
            when(iteratorMock.hasNext()).thenReturn(false);
            LoanPlans loanPlansMock = mock(LoanPlans.class);
            when(iteratorMock.next()).thenReturn(loanPlansMock);
            when(loanPlansMock.getPlanId()).thenReturn(1);
            when(loanPlansMock.getPlanName()).thenReturn("Home");
            when(loanPlansMock.getPrincipleAmount()).thenReturn(1000000);
            when(loanPlansMock.getTenure()).thenReturn(90);
            when(loanPlansMock.getInterestRate()).thenReturn(8.5F);
            when(loanPlansMock.getInterestAmount()).thenReturn(85000);
            when(loanPlansMock.getTotalPayable()).thenReturn(1085000);
            when(loanPlansMock.getEmi()).thenReturn(950.0F);
            when(loanPlansMock.getPlanValidity()).thenReturn(LocalDate.now());

            List<LoanPlansDTO> loanPlansDTOList = loanPlansServiceImpl.fetchLoanPlans();
            assertTrue(loanPlansDTOList.size() == 0);
        }
        catch(Exception e){
            e.printStackTrace();
            assertTrue(false);
        }
    }


    @Test
    void testFetchLoanPlanById_Positive() {
        try {
            BaseInterestRates baseInterestRates =mock(BaseInterestRates.class);
            baseInterestRates.setId(1);
            baseInterestRates.setLoanType("Home");
            baseInterestRates.setBaseInterestRate(8.5f);

            int planIdTestData = 10;
            LoanPlans loanPlans = new LoanPlans();
            loanPlans.setPlanId(10);
            loanPlans.setPlanName("Home");
            loanPlans.setPrincipleAmount(1000000);
            loanPlans.setTenure(90);
            loanPlans.setInterestRate(12.1F);
            loanPlans.setInterestAmount(84700);
            loanPlans.setTotalPayable(1847000);
            loanPlans.setEmi(20522.223F);
            loanPlans.setPlanValidity(LocalDate.of(2026, Month.APRIL, 12));
            loanPlans.setPlanAddedOn(LocalDate.now());
            loanPlans.setBaseInterestRates(baseInterestRates);

            Optional<LoanPlans> optionalLoanPlans = Optional.of(loanPlans);
            when(loanPlansRepository.findById(planIdTestData)).thenReturn(optionalLoanPlans);

            LoanPlans loanPlansMock = mock(LoanPlans.class);
            when(loanPlansMock.getPlanId()).thenReturn(10);
            when(loanPlansMock.getPlanName()).thenReturn("Home");
            when(loanPlansMock.getPrincipleAmount()).thenReturn(1000000);
            when(loanPlansMock.getTenure()).thenReturn(90);
            when(loanPlansMock.getInterestRate()).thenReturn(8.5F);
            when(loanPlansMock.getInterestAmount()).thenReturn(85000);
            when(loanPlansMock.getTotalPayable()).thenReturn(1085000);
            when(loanPlansMock.getEmi()).thenReturn(950.0F);
            when(loanPlansMock.getPlanValidity()).thenReturn(LocalDate.of(2026, Month.APRIL, 12));
            when(loanPlansMock.getPlanAddedOn()).thenReturn(LocalDate.now());
            when(loanPlansMock.getBaseInterestRates()).thenReturn(baseInterestRates);


            LoanPlansDTO loanPlansDTO = loanPlansServiceImpl.fetchLoanPlanById(planIdTestData);
            assertEquals(loanPlansDTO.getPlanId(), loanPlansMock.getPlanId());

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testFetchLoanPlanById_Negative(){
        when(loanPlansRepository.findById(any())).thenReturn(Optional.empty());
        LoanPlans loanPlansMock =  mock(LoanPlans.class);
        when(loanPlansMock.getPlanId()).thenReturn(10);
        when(loanPlansMock.getPlanName()).thenReturn("Home");
        when(loanPlansMock.getPrincipleAmount()).thenReturn(1000000);
        when(loanPlansMock.getTenure()).thenReturn(90);
        when(loanPlansMock.getInterestRate()).thenReturn(8.5F);
        when(loanPlansMock.getInterestAmount()).thenReturn(85000);
        when(loanPlansMock.getTotalPayable()).thenReturn(1085000);
        when(loanPlansMock.getEmi()).thenReturn(950.0F);
        when(loanPlansMock.getPlanValidity()).thenReturn(LocalDate.of(2026, Month.APRIL,12));
        when(loanPlansMock.getPlanAddedOn()).thenReturn(LocalDate.now());

        LoanPlansDTO loanPlansDTO =  loanPlansServiceImpl.fetchLoanPlanById(1);
        assertEquals(0,loanPlansDTO.getPlanId());
    }

    @Test
    void testUpdateLoanPlan_Succeess() {
        try{
            LoanPlans loanPlans = new LoanPlans();
            loanPlans.setPlanId(10);
            loanPlans.setPlanName("Home");
            loanPlans.setPlanValidity(LocalDate.of(2026, Month.APRIL,12));

            when(loanPlansRepository.findById(10)).thenReturn(Optional.of(loanPlans));

            LoanPlans updatedLoanPlans = new LoanPlans();
            updatedLoanPlans.setPlanId(10);
            updatedLoanPlans.setPlanName("Personal");
            updatedLoanPlans.setPlanValidity(LocalDate.of(2027, Month.APRIL,12));

            when(loanPlansRepository.save(any(LoanPlans.class))).thenReturn(updatedLoanPlans);

            LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
            loanPlansDTO.setPlanId(10);
            loanPlansDTO.setPlanName("Personal");
            loanPlansDTO.setPlanValidity(LocalDate.of(2027, Month.APRIL,12));

            String actual = loanPlansServiceImpl.updateLoanPlan(10,loanPlansDTO);
            assertEquals("success",actual);
            assertEquals(loanPlansDTO.getPlanId(), updatedLoanPlans.getPlanId());
        }
        catch (Exception e){
            assertTrue(false);
        }
    }
    @Test
    void testUpdateLoanPlan_Failure() {
        try{
            when(loanPlansRepository.findById(any())).thenReturn(Optional.empty());
            LoanPlansDTO loanPlansDTO = new LoanPlansDTO();
            loanPlansDTO.setPlanName("Home");
            loanPlansDTO.setPlanValidity(LocalDate.of(2027, Month.APRIL,12));
            String actual = loanPlansServiceImpl.updateLoanPlan(10,loanPlansDTO);
            assertEquals("fail",actual);
        }
        catch (Exception e){
            assertTrue(false);
        }
    }
    @Test
    void testCalculateTotalPayable_Positive(){
        int principleAmount = 100000;
        double year = 2;
        float interestRate = 8.5f;
        double expected = (principleAmount * (interestRate / 100) * year) + principleAmount;
        double actual = loanPlansServiceImpl.calculateTotalPayable(principleAmount,year,interestRate);
        assertEquals(expected,actual);
    }

    @Test
    void testCalculateInterestAmountAndTotalPayable_ForPrincipleAmountLessThan500000_Home() {
        LoanPlans loanPlans = Mockito.mock(LoanPlans.class);
        LoanPlansServiceImpl loanPlansService = new LoanPlansServiceImpl();

        loanPlansService.calculateInterestRateAndInterestAmountAndEMI("Home", 300000, 36, loanPlans);

        verify(loanPlans).setInterestRate(8.5f);
        verify(loanPlans).setInterestAmount(76500);
        verify(loanPlans).setTotalPayable(376500);
        verify(loanPlans).setEmi(10458.333f);
    }

    @Test
    void testCalculateInterestAmountAndTotalPayable_ForPrincipleAmountGreaterThan500000_Home() {
        LoanPlans loanPlans = Mockito.mock(LoanPlans.class);
        LoanPlansServiceImpl loanPlansService = new LoanPlansServiceImpl();

        loanPlansService.calculateInterestRateAndInterestAmountAndEMI("Home", 700000, 72, loanPlans);

        verify(loanPlans).setInterestRate(9.7f);
        verify(loanPlans).setInterestAmount(407400);
        verify(loanPlans).setTotalPayable(1107400);
        verify(loanPlans).setEmi(15380.556f);
    }

    @Test
    void testCalculateInterestAmountAndTotalPayable_ForPrincipleAmountLessThan500000_Personal() {
        LoanPlans loanPlans = Mockito.mock(LoanPlans.class);
        LoanPlansServiceImpl loanPlansService = new LoanPlansServiceImpl();

        loanPlansService.calculateInterestRateAndInterestAmountAndEMI("Personal", 300000, 36, loanPlans);

        verify(loanPlans).setInterestRate(10.0f);
        verify(loanPlans).setInterestAmount(90000);
        verify(loanPlans).setTotalPayable(390000);
        verify(loanPlans).setEmi(10833.333f);
    }

    @Test
    void testCalculateInterestAmountAndTotalPayable_ForPrincipleAmountGreaterThan500000_Personal() {
        LoanPlans loanPlans = Mockito.mock(LoanPlans.class);
        LoanPlansServiceImpl loanPlansService = new LoanPlansServiceImpl();

        loanPlansService.calculateInterestRateAndInterestAmountAndEMI("Personal", 700000, 72, loanPlans);

        verify(loanPlans).setInterestRate(11.8f);
        verify(loanPlans).setInterestAmount(495600);
        verify(loanPlans).setTotalPayable(1195600);
        verify(loanPlans).setEmi(16605.555f);
    }

    @Test
    void testCalculateInterestAmountAndTotalPayable_ForPrincipleAmountLessThan500000_Medical() {
        LoanPlans loanPlans = Mockito.mock(LoanPlans.class);
        LoanPlansServiceImpl loanPlansService = new LoanPlansServiceImpl();

        loanPlansService.calculateInterestRateAndInterestAmountAndEMI("Medical", 300000, 36, loanPlans);

        verify(loanPlans).setInterestRate(7.5f);
        verify(loanPlans).setInterestAmount(67500);
        verify(loanPlans).setTotalPayable(367500);
        verify(loanPlans).setEmi(10208.333f);
    }

    @Test
    void testCalculateInterestAmountAndTotalPayable_ForPrincipleAmountGreaterThan500000_Medical() {
        LoanPlans loanPlans = Mockito.mock(LoanPlans.class);
        LoanPlansServiceImpl loanPlansService = new LoanPlansServiceImpl();

        loanPlansService.calculateInterestRateAndInterestAmountAndEMI("Medical", 700000, 72, loanPlans);

        verify(loanPlans).setInterestRate(9.0f);
        verify(loanPlans).setInterestAmount(378000);
        verify(loanPlans).setTotalPayable(1078000);
        verify(loanPlans).setEmi(14972.223f);
    }

    @Test
    void testCalculateInterestAmountAndTotalPayable_ForPrincipleAmountLessThan500000_Vehicle() {
        LoanPlans loanPlans = Mockito.mock(LoanPlans.class);
        LoanPlansServiceImpl loanPlansService = new LoanPlansServiceImpl();

        loanPlansService.calculateInterestRateAndInterestAmountAndEMI("Vehicle", 300000, 36, loanPlans);

        verify(loanPlans).setInterestRate(8.0f);
        verify(loanPlans).setInterestAmount(72000);
        verify(loanPlans).setTotalPayable(372000);
        verify(loanPlans).setEmi(10333.333f);
    }

    @Test
    void testCalculateInterestAmountAndTotalPayable_ForPrincipleAmountGreaterThan500000_Vehicle() {
        LoanPlans loanPlans = Mockito.mock(LoanPlans.class);
        LoanPlansServiceImpl loanPlansService = new LoanPlansServiceImpl();

        loanPlansService.calculateInterestRateAndInterestAmountAndEMI("Vehicle", 700000, 72, loanPlans);

        verify(loanPlans).setInterestRate(9.5f);
        verify(loanPlans).setInterestAmount(399000);
        verify(loanPlans).setTotalPayable(1099000);
        verify(loanPlans).setEmi(15263.889f);
    }
    }
