package com.rentmycar.rentmycar.model;

import com.rentmycar.rentmycar.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void isUserTestDataGenerated() {
        System.out.println("Generating test users...");
        long count = userRepository.count();
        System.out.println("Elements: " + count);
        userRepository.findAll().forEach(System.out::println);
        System.out.println("Test users successfully generated.");

        assertEquals(count, 10);
    }
}