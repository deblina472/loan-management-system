package com.cognizant.services;

import com.cognizant.dto.BaseInterestRatesDTO;
import com.cognizant.entities.BaseInterestRates;
import com.cognizant.repositories.BaseInterestRatesRepository;
import com.cognizant.services.classes.BaseInterestRatesServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BaseInterestRatesServiceImplTest {
    @Mock
    private BaseInterestRatesRepository baseInterestRatesRepository;
    @InjectMocks
    private BaseInterestRatesServiceImpl baseInterestRatesServiceImpl;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception{}
    @Test
    public void testGetInterestRateList_Positive_WhenDTOIsFound() {
        try {
            Iterable<BaseInterestRates> iterableMock = Mockito.mock(Iterable.class);
            when(baseInterestRatesRepository.findAll()).thenReturn(iterableMock);
            Iterator<BaseInterestRates> iteratorMock = Mockito.mock(Iterator.class);
            when(iterableMock.iterator()).thenReturn(iteratorMock);
            when(iteratorMock.hasNext()).thenReturn(true).thenReturn(false);
            BaseInterestRates baseInterestRatesMock = Mockito.mock(BaseInterestRates.class);
            when(iteratorMock.next()).thenReturn(baseInterestRatesMock);
            when(baseInterestRatesMock.getId()).thenReturn(1);
            when(baseInterestRatesMock.getLoanType()).thenReturn("Home");
            when(baseInterestRatesMock.getBaseInterestRate()).thenReturn(8.5F);

            List<BaseInterestRatesDTO> baseInterestRatesDTOList = baseInterestRatesServiceImpl.getInterestRateList();
            assertTrue(baseInterestRatesDTOList.size()==1);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testGetInterestRateList_Positive_WhenMoreThanOneDTOIsFound() {
        try {
            Iterable<BaseInterestRates> iterableMock = Mockito.mock(Iterable.class);
            when(baseInterestRatesRepository.findAll()).thenReturn(iterableMock);
            Iterator<BaseInterestRates> iteratorMock = Mockito.mock(Iterator.class);
            when(iterableMock.iterator()).thenReturn(iteratorMock);
            when(iteratorMock.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
            BaseInterestRates baseInterestRatesMock = Mockito.mock(BaseInterestRates.class);
            when(iteratorMock.next()).thenReturn(baseInterestRatesMock);
            when(baseInterestRatesMock.getId()).thenReturn(1);
            when(baseInterestRatesMock.getLoanType()).thenReturn("Home");
            when(baseInterestRatesMock.getBaseInterestRate()).thenReturn(8.5F);

            List<BaseInterestRatesDTO> list = baseInterestRatesServiceImpl.getInterestRateList();
            assertTrue(list.size() > 1);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void testGetInterestRateList_Negative(){
        try {
            Iterable<BaseInterestRates> iterableMock = Mockito.mock(Iterable.class);
            when(baseInterestRatesRepository.findAll()).thenReturn(iterableMock);
            Iterator<BaseInterestRates> iteratorMock = Mockito.mock(Iterator.class);
            when(iterableMock.iterator()).thenReturn(iteratorMock);
            when(iteratorMock.hasNext()).thenReturn(false);
            BaseInterestRates baseInterestRatesMock = Mockito.mock(BaseInterestRates.class);
            when(baseInterestRatesMock.getId()).thenReturn(1);
            when(baseInterestRatesMock.getLoanType()).thenReturn("Home");
            when(baseInterestRatesMock.getBaseInterestRate()).thenReturn(8.5F);

            List<BaseInterestRatesDTO> baseInterestRatesDTOList = baseInterestRatesServiceImpl.getInterestRateList();
            assertTrue(baseInterestRatesDTOList.size() == 0);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
//    @Test
//    public void testGetInterestRateList_Exception() {
//        try {
//            Iterable<BaseInterestRates> iterableMock = Mockito.mock(Iterable.class);
//            when(baseInterestRatesRepository.findAll()).thenReturn(iterableMock);
//            Iterator<BaseInterestRates> iteratorMock = Mockito.mock(Iterator.class);
//            when(iterableMock.iterator()).thenReturn(iteratorMock);
//            when(iteratorMock.hasNext()).thenReturn(false);
//            BaseInterestRates baseInterestRatesMock = Mockito.mock(BaseInterestRates.class);
//            when(iteratorMock.next()).thenReturn(baseInterestRatesMock);
//            when(baseInterestRatesMock.getId()).thenReturn(1);
//            when(baseInterestRatesMock.getLoanType()).thenReturn("Home");
//            when(baseInterestRatesMock.getBaseInterestRate()).thenReturn(8.5F);
//
//            List<BaseInterestRatesDTO> list = baseInterestRatesServiceImpl.getInterestRateList();
//            assertTrue(false);
//        } catch (Exception e) {
//            assertTrue(true);
//        }
//    }

}
