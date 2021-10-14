package com.rentmycar.rentmycar.user;

import com.rentmycar.rentmycar.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void userTestData() {
        System.out.println("Generating test users...");
        long count = userRepository.count();
        System.out.println("Elements: " + count);
        userRepository.findAll().forEach(System.out::println);
        System.out.println("Test users successfully generated.");
    }
}