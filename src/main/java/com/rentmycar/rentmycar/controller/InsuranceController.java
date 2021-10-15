package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.Insurance;
import com.rentmycar.rentmycar.service.InsuranceService;
import com.rentmycar.rentmycar.util.ReservationNumberGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1.0/insurance/")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    public List<Insurance> getInsuranceTypes() {
        return insuranceService.getInsuranceTypes();
    }
}
