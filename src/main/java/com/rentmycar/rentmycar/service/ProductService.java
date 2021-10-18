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
    private final CarTimeslotAvailabilityService carTimeslotAvailabilityService;

    public ProductDto createProduct(ProductRequestDto productRequest, Reservation reservation) {
        List<CarTimeslotAvailability> timeslots = productRequest.getTimeslots();

        // Loop through selected timeslots and calculate price. Add to total.
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CarTimeslotAvailability timeslot: timeslots) {
            CarTimeslotAvailability productTimeslot = getTimeslot(timeslot);

            BigDecimal hours = new BigDecimal(Duration.between(productTimeslot.getStartAt(), productTimeslot.getEndAt()).toHours());
            BigDecimal pricePerHour = productTimeslot.getRentalPlan().getPrice();
            BigDecimal price = hours.multiply(pricePerHour);

            totalPrice = totalPrice.add(price);
        }

        // Create product
        Product product = new Product(
            reservation,
            totalPrice,
            productRequest.getRentalPlan(),
            productRequest.getInsuranceTypeId(),
            productRequest.getInsurancePrice(),
            ProductStatus.PENDING
        );
        Product createdProduct = productRepository.save(product);

        // Update timeslots with product_id to secure the reservation
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

    public void completeProduct(Reservation reservation) {
        Product product = getProduct(reservation);

        product.setStatus(ProductStatus.COMPLETED);
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Product could not be updated.");
        }
    }

    public void cancelProduct(Reservation reservation) {
        Product product = getProduct(reservation);
        product.setStatus(ProductStatus.CANCELED);

        carTimeslotAvailabilityService.cancelTimeslotReservation(product);

        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Product could not be updated.");
        }
    }

    public Product getProduct(Reservation reservation) {
        Optional<Product> productOptional = productRepository.getByReservation(reservation);

        if (productOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product could not be found.");
        }
        return productOptional.get();
    }
}
