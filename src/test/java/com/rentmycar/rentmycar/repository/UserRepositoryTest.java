package com.rentmycar.rentmycar.repository;

import com.rentmycar.rentmycar.model.User;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {
    @Test
    void findUserByEmail() {
        UserRepository userRepository = mock(UserRepository.class);
        User user = mock(User.class);
        when (user.getEmail()).thenReturn("harryhire@rent.com");

        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        assertNotNull(findUser);
    }
}
