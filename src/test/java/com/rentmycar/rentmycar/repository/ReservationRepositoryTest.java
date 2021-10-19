package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.Reservation;
import com.rentmycar.rentmycar.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReservationRepositoryTest {

    @Mock
    private ReservationRepository sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllByUser() {
        Reservation reservation = mock(Reservation.class);
        sut.save(reservation);
        User user = mock(User.class);
        when(reservation.getUser()).thenReturn(user);

        List<Reservation> expected = sut.findAllByUser(user);

        assertNotNull(expected);
    }
}