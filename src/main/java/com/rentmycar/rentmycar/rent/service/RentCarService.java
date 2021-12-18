package com.rentmycar.rentmycar.rent.service;

import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.rent.dto.RentCarAvailabilityDto;
import com.rentmycar.rentmycar.rent.dto.RentCarDto;

import com.rentmycar.rentmycar.rent.dto.RentCarListDto;
import com.rentmycar.rentmycar.rent.repository.RentCarAvailabilityRepository;
import com.rentmycar.rentmycar.rent.repository.RentCarRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
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

    public CarDto getCarById(Long id) {
        Car car = rentCarRepository.getById(id);

        return modelMapper.map(car, CarDto.class);
    }

    public Page<RentCarAvailabilityDto> getCarAvailability(Long id, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
            Car car = rentCarRepository.getById(id);
        Page<CarTimeslotAvailability> carAvailability =  rentCarAvailabilityRepository.getCarAvailability(car, page);
        return carAvailability.map(new Function<CarTimeslotAvailability, RentCarAvailabilityDto>() {

            @Override
            public RentCarAvailabilityDto apply(CarTimeslotAvailability carTimeslotAvailability) {
                return modelMapper.map(carTimeslotAvailability, RentCarAvailabilityDto.class);
            }
        });
    }
}
