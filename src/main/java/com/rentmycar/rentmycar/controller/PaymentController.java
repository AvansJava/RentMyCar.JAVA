package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.CallbackDto;
import com.rentmycar.rentmycar.dto.PaymentDto;
import com.rentmycar.rentmycar.enums.PaymentStatus;
import com.rentmycar.rentmycar.model.Payment;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.PaymentService;
import com.rentmycar.rentmycar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="api/v1.0/payment/")
public class PaymentController {

    private final UserService userService;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> postPayment(@RequestBody Payment payment) {
        User user = userService.getAuthenticatedUser();

        return paymentService.createPayment(payment, user);
    }

    @PostMapping(path = "{id}/callback/")
    public ResponseEntity<String> receivePaymentCallback(@PathVariable("id") Long id, @RequestBody CallbackDto callback) {
        return paymentService.processCallback(id, callback);
    }

    @GetMapping(path = "{id}/")
    public PaymentDto getPayment(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return paymentService.getPaymentByUser(id, user);
    }

    @GetMapping
    public List<PaymentDto> getPayments(@RequestParam(required = false) PaymentStatus status) {
        User user = userService.getAuthenticatedUser();
        return paymentService.getAllPaymentsByUser(status, user);
    }
}
