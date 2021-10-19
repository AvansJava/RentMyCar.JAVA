package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.repository.InsuranceRepository;
import com.rentmycar.rentmycar.repository.TimeslotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class InsuranceServiceTest {

    @Mock
    private InsuranceRepository insuranceRepository;
    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        insuranceService = new InsuranceService(insuranceRepository);
    }

    @Test
    void getInsuranceTypes() {
        insuranceService.getInsuranceTypes();
        verify(insuranceRepository).findAll();
    }
}