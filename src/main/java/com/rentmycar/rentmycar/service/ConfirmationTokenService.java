package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.ConfirmationToken;
import com.rentmycar.rentmycar.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Getter
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(UUID token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(UUID token) {
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
