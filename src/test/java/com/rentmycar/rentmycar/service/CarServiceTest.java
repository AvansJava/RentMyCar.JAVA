package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.repository.CarRepository;
import com.rentmycar.rentmycar.repository.CarResourceRepository;
import com.rentmycar.rentmycar.repository.RentalPlanRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    private LocationService locationService;
    private ModelMapper modelMapper;
    private RentalPlanRepository rentalPlanRepository;
    private FileUploadService fileUploadService;
    private CarResourceRepository carResourceRepository;
    private CarService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CarService(carRepository, locationService, modelMapper, rentalPlanRepository, fileUploadService, carResourceRepository);

    }

    @Test
    void canGetAllCars() {
        underTest.getCarsList();
        verify(carRepository).findAll();
    }

}
