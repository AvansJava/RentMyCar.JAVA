package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.Timeslot;
import com.rentmycar.rentmycar.repository.TimeslotRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class TimeslotServiceTest {

    @Mock
    private TimeslotRepository timeslotRepository;
    private TimeslotService timeslotServiceTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        timeslotServiceTest = new TimeslotService(timeslotRepository);
    }

    @Test
    void canGetTimeslots() {
        timeslotServiceTest.getTimeslots();
        verify(timeslotRepository).findAll();
    }
}