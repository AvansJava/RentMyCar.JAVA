package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.enums.CarType;
import com.rentmycar.rentmycar.enums.UserRole;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.Location;
import com.rentmycar.rentmycar.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findAllByUser() {
        User user = new User(
                "Robby",
                "Renter",
                "robby@renter.com",
                "robwillrent123",
                UserRole.USER
        );

        Location location = new Location(
                "Ernst Casimirstraat",
                "21",
                "4811KS",
                "Breda",
                "Nederland",
                51.585154037140306f,
                4.770362791146651f,
                user
        );

        Car car = new Car(
                "Honda",
                "H_RV",
                "EX_L",
                "465-HK-3",
                5.7,
                33450,
                CarType.FCEV,
                user,
                location
        );

        underTest.save(car);

        List<Car> expected = underTest.findAllByUser(user);

        assertThat(expected).isEqualTo(car);
    }
}