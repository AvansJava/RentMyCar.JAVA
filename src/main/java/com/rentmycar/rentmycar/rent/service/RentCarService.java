package com.rentmycar.rentmycar.rent.service;

import com.rentmycar.rentmycar.common.model.Car;
import com.rentmycar.rentmycar.rent.dto.RentCarDto;
import com.rentmycar.rentmycar.rent.dto.RentCarListDto;
import com.rentmycar.rentmycar.rent.repository.RentCarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentCarService {

    private final RentCarRepository rentCarRepository;
    private final ModelMapper modelMapper;

    public RentCarService(RentCarRepository rentCarRepository, ModelMapper modelMapper) {
        this.rentCarRepository = rentCarRepository;
        this.modelMapper = modelMapper;
    }

    public RentCarDto getCarById(Long id) {
        Car car = rentCarRepository.getById(id);

        return modelMapper.map(car, RentCarDto.class);
    }
}
