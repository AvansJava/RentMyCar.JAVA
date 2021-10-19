package com.rentmycar.rentmycar.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    EmailValidator validator = new EmailValidator();

    @Test
    void email_validator_expect_false() {
        boolean isValidEmail = validator.test("ThisisNoEmail");

        assertFalse(isValidEmail);
    }

    @Test
    void email_validator_expect_true() {
        boolean isValidEmail = validator.test("thisis@anemail.com");

        assertTrue(isValidEmail);
    }
}