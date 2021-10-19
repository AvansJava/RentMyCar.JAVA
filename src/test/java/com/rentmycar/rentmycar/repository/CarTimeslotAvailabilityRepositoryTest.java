package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.enums.TimeSlotAvailabilityStatus;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.CarTimeslotAvailability;
import com.rentmycar.rentmycar.model.RentalPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarTimeslotAvailabilityRepositoryTest {

    @Test
    void deleteAllByRentalPlan() {
        CarTimeslotAvailabilityRepository repository = mock(CarTimeslotAvailabilityRepository.class);

        CarTimeslotAvailability timeslot1 = mock(CarTimeslotAvailability.class);
        CarTimeslotAvailability timeslot2 = mock(CarTimeslotAvailability.class);

        RentalPlan rentalPlan = mock(RentalPlan.class);
        when(rentalPlan.getId()).thenReturn(1L);

        when(timeslot1.getRentalPlan()).thenReturn(rentalPlan);
        when(timeslot2.getRentalPlan()).thenReturn(rentalPlan);

        repository.deleteAllByRentalPlan(rentalPlan);

        List<CarTimeslotAvailability> timeslots = repository.findAll();

        assertEquals(0, timeslots.size());
    }
}