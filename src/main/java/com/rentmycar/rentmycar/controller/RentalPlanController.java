package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.dto.RentalPlanDto;
import com.rentmycar.rentmycar.model.Car;
import com.rentmycar.rentmycar.model.RentalPlan;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.RentalPlanService;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1.0/rentalplan/")
public class RentalPlanController {
    private final RentalPlanService rentalPlanService;
    private final UserService userService;

    @Autowired
    public RentalPlanController(RentalPlanService rentalPlanService, UserService userService) {
        this.rentalPlanService = rentalPlanService;
        this.userService = userService;
    }

    @GetMapping
    public List<RentalPlanDto> getRentalPlansByUser() {
        User user = userService.getAuthenticatedUser();
        return rentalPlanService.getRentalPlansByUser(user);
    }

    @GetMapping(path = "{id}/")
    public RentalPlanDto getRentalPlan(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return rentalPlanService.getRentalPlan(id, user);
    }

    @PostMapping
    public ResponseEntity<RentalPlanDto> postRentalPlan(@RequestBody RentalPlan rentalPlan) {
        User user = userService.getAuthenticatedUser();
        return rentalPlanService.createRentalPlan(rentalPlan, user);
    }
}
