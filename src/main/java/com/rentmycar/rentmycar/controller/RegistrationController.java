package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.request.RegistrationRequest;
import com.rentmycar.rentmycar.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1.0/auth/register")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public UUID register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") UUID token) {
        return registrationService.confirmToken(token);
    }
}
