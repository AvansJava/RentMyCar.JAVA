package com.rentmycar.rentmycar.rent.service;

import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.rent.dto.RentCarListDto;
import com.rentmycar.rentmycar.rent.repository.RentCarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentCarListService {
    private final RentCarRepository rentCarRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RentCarListService(RentCarRepository rentCarRepository, ModelMapper modelMapper) {
        this.rentCarRepository = rentCarRepository;
        this.modelMapper = modelMapper;
    }

    public List<CarDto> getCarList() {
        return rentCarRepository.findAll()
                .stream()
                .map(obj -> modelMapper.map(obj, CarDto.class))
                .collect(Collectors.toList());
    }
}
