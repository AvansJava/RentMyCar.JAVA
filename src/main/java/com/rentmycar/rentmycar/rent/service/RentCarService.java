package com.rentmycar.rentmycar.rent.service;

import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.rent.dto.RentCarAvailabilityDto;
import com.rentmycar.rentmycar.rent.dto.RentCarDto;

import com.rentmycar.rentmycar.rent.dto.RentCarListDto;
import com.rentmycar.rentmycar.rent.repository.RentCarAvailabilityRepository;
import com.rentmycar.rentmycar.rent.repository.RentCarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentCarService {

    private final RentCarRepository rentCarRepository;
    private final RentCarAvailabilityRepository rentCarAvailabilityRepository;
    private final ModelMapper modelMapper;

    public RentCarService(RentCarRepository rentCarRepository, RentCarAvailabilityRepository rentCarAvailabilityRepository, ModelMapper modelMapper) {
        this.rentCarRepository = rentCarRepository;
        this.rentCarAvailabilityRepository = rentCarAvailabilityRepository;
        this.modelMapper = modelMapper;
    }

    public RentCarDto getCarById(Long id) {
        Car car = rentCarRepository.getById(id);

        return modelMapper.map(car, RentCarDto.class);
    }

    public List<RentCarAvailabilityDto> getCarAvailability(Long id) {
        Car car = rentCarRepository.getById(id);
        List<CarTimeslotAvailability> carAvailability =  rentCarAvailabilityRepository.getCarAvailability(car);
        return carAvailability.stream()
                .map(obj -> modelMapper.map(obj, RentCarAvailabilityDto.class))
                .collect(Collectors.toList());
    }
}
