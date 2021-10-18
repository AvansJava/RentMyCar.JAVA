package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.dto.TcoDto;
import com.rentmycar.rentmycar.enums.CarType;
import com.rentmycar.rentmycar.model.*;
import com.rentmycar.rentmycar.repository.CarResourceRepository;
import com.rentmycar.rentmycar.repository.RentalPlanRepository;
import org.modelmapper.ModelMapper;

import com.rentmycar.rentmycar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final LocationService locationService;
    private final ModelMapper modelMapper;
    private final RentalPlanRepository rentalPlanRepository;
    private final FileUploadService fileUploadService;
    private final CarResourceRepository carResourceRepository;

    @Autowired
    public CarService(CarRepository carRepository, LocationService locationService, ModelMapper modelMapper, RentalPlanRepository rentalPlanRepository, FileUploadService fileUploadService, CarResourceRepository carResourceRepository) {
        this.carRepository = carRepository;
        this.locationService = locationService;
        this.modelMapper = modelMapper;
        this.rentalPlanRepository = rentalPlanRepository;
        this.fileUploadService = fileUploadService;
        this.carResourceRepository = carResourceRepository;
    }

    public List<CarDto> getCarsList() {
        return carRepository.findAll()
                .stream()
                .map(obj -> modelMapper.map(obj, CarDto.class))
                .collect(Collectors.toList());
    }

    public ResponseEntity<CarDto> createCar(Car car, User user) {
        String licensePlateNumber = car.getLicensePlateNumber();
        Optional<Car> carOptional = carRepository.findCarByLicensePlateNumber(licensePlateNumber);

        if (carOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A car with license plate number " + licensePlateNumber + "already exists.");
        }
        car.setUser(user);

        // If location is in post request, create that as well.
        Location location = car.getLocation();
        if (location != null) {
            locationService.createLocation(location, user);
        }
        Car createdCar = carRepository.save(car);
        return new ResponseEntity<>(modelMapper.map(createdCar, CarDto.class), HttpStatus.CREATED);
    }

    public CarDto updateCar(Long id, Car newCar, User user) {
        Car car = findCarByUser(id, user);

        car.setBrand(newCar.getBrand());
        car.setBrandType(newCar.getBrandType());
        car.setModel(newCar.getModel());
        car.setLicensePlateNumber(newCar.getLicensePlateNumber());
        car.setConsumption(newCar.getConsumption());
        car.setCostPrice(newCar.getCostPrice());
        car.setCarType(newCar.getCarType());

        return modelMapper.map(carRepository.save(car), CarDto.class);
    }

    public List<CarDto> getCarsByUser(User user) {
        return carRepository.findAllByUser(user)
                .stream()
                .map(obj -> modelMapper.map(obj, CarDto.class))
                .collect(Collectors.toList());
    }

    public CarDto getCarByUser(Long id, User user) {
        Car car = findCarByUser(id, user);
        return modelMapper.map(car, CarDto.class);
    }

    public ResponseEntity<String> deleteCar(Long id, User user) {
        Car car = findCarByUser(id, user);

        Optional<RentalPlan> rentalPlan = rentalPlanRepository.findAllByCar(car);
        if (rentalPlan.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Car can't be deleted because it has active rental plans.");
        }

        carRepository.delete(car);
        return new ResponseEntity<>("Car successfully deleted.", HttpStatus.OK);
    }

    public TcoDto calculateCarTco(Long id, User user, int km) {
        Car car = findCarByUser(id, user);

        // Cars depreciate at an average of 20% year. This method uses that average.
        Double depreciation = car.getCostPrice() * 0.2;

        // costPerUnit are fixed prices.
        // TODO: integrate external API for up-to-date electricity, hydrogen and fuel costs.
        CarType carType = car.getCarType();
        double costPerUnit = 0.00;
        switch (carType) {
            case BEV:
                costPerUnit = 0.2; // €/kWh
                break;
            case ICE:
                costPerUnit = 1.75; // €/l
                break;
            case FCEV:
                costPerUnit = 2.03; // €/kg
                break;
        }

        // Calculates yearly fuel cost based on costPerUnit of carType and consumption and provided kms in request
        Double yearlyFuelCost = costPerUnit * car.getConsumption() * (km / 100);
        Double totalCostOwnership = depreciation + yearlyFuelCost;
        Double costPerKilometer = (car.getConsumption() / 100) * costPerUnit;

        return new TcoDto (
                car.getId(),
                car.getConsumption(),
                km,
                car.getCostPrice(),
                depreciation,
                yearlyFuelCost,
                totalCostOwnership,
                costPerKilometer
        );
    }

    public ResponseEntity<String> uploadImage(Long id, MultipartFile file, User user) {
        Car car = findCarByUser(id, user);
        String filePath = fileUploadService.uploadImage(file);

        CarResource carResource = new CarResource(filePath,car);
        carResourceRepository.save(carResource);

        return new ResponseEntity<>("File successfully uploaded", HttpStatus.CREATED);
    }

    private Car findCarByUser(Long id, User user) {
        Optional<Car> carOptional = carRepository.findById(id);
        if(carOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found.");
        }
        Car car = carOptional.get();

        if (car.getUser() != user) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Car does not belong to user.");
        }
        return car;
    }
}
