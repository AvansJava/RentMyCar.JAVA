package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.Insurance;
import com.rentmycar.rentmycar.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    public InsuranceService(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }

    public List<Insurance> getInsuranceTypes() {
        return insuranceRepository.findAll();
    }
}
