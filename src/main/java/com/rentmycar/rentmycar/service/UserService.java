package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.ConfirmationToken;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws ResponseStatusException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with e-mail address " + email +
                        " could not be found."));
    }

    public UUID registerUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        // Create confirmation token
        UUID token = UUID.randomUUID();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(60),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    public User getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal().toString();

        return userRepository.findByEmail(email).stream().findFirst().orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with e-mail address " + email +
                        " could not be found."));
    }

    public User updateUser(User user) {
        return this.userRepository.save(user);
    }
}
