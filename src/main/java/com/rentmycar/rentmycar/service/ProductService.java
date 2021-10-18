package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.dto.ProductDto;
import com.rentmycar.rentmycar.dto.ProductRequestDto;
import com.rentmycar.rentmycar.enums.ProductStatus;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.Product;
import com.rentmycar.rentmycar.model.RentalPlan;
import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.repository.CarTimeslotAvailabilityRepository;
import com.rentmycar.rentmycar.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CarTimeslotAvailabilityRepository carTimeslotAvailabilityRepository;
    private final ModelMapper modelMapper;

    public ProductDto createProduct(ProductRequestDto productRequest, Reservation reservation) {
        List<CarTimeslotAvailability> timeslots = productRequest.getTimeslots();

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CarTimeslotAvailability timeslot: timeslots) {
            CarTimeslotAvailability productTimeslot = getTimeslot(timeslot);

            BigDecimal hours = new BigDecimal(Duration.between(productTimeslot.getStartAt(), productTimeslot.getEndAt()).toHours());
            BigDecimal pricePerHour = productTimeslot.getRentalPlan().getPrice();
            BigDecimal price = hours.multiply(pricePerHour);

            totalPrice = totalPrice.add(price);
        }

        Product product = new Product(
            reservation,
            totalPrice,
            productRequest.getRentalPlan(),
            productRequest.getInsuranceTypeId(),
            productRequest.getInsurancePrice(),
            ProductStatus.PENDING
        );
        Product createdProduct = productRepository.save(product);

        for (CarTimeslotAvailability timeslot: timeslots) {
            carTimeslotAvailabilityRepository.updateWithProduct(createdProduct, getTimeslot(timeslot).getId());
        }

        return modelMapper.map(createdProduct, ProductDto.class);
    }

    public CarTimeslotAvailability getTimeslot(CarTimeslotAvailability timeslot) {
        Optional<CarTimeslotAvailability> carTimeslotAvailability = carTimeslotAvailabilityRepository.findById(timeslot.getId());

        if (carTimeslotAvailability.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Timeslot could not be found.");
        }

        return carTimeslotAvailability.get();
    }
}
