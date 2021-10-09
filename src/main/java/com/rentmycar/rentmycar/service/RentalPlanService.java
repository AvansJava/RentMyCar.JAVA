package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.CarList;
import com.rentmycar.rentmycar.dto.RentalPlanDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.RentalPlan;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.RentalPlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalPlanService {

    private final RentalPlanRepository rentalPlanRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RentalPlanService(RentalPlanRepository rentalPlanRepository, ModelMapper modelMapper) {
        this.rentalPlanRepository = rentalPlanRepository;
        this.modelMapper = modelMapper;
    }


    public List<RentalPlanDto> getRentalPlansByUser(User user) {
        return rentalPlanRepository.findAllByUser(user)
                .stream()
                .map(obj -> modelMapper.map(obj, RentalPlanDto.class))
                .collect(Collectors.toList());
    }

    public RentalPlanDto getRentalPlan(Long id, User user) {
    }
}
