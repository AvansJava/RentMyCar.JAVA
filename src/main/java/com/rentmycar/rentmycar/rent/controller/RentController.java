package com.rentmycar.rentmycar.rent.controller;

import com.rentmycar.rentmycar.common.model.Car;
import com.rentmycar.rentmycar.common.model.User;
import com.rentmycar.rentmycar.common.service.UserService;
import com.rentmycar.rentmycar.rent.dto.RentCarDto;
import com.rentmycar.rentmycar.rent.dto.RentCarListDto;
import com.rentmycar.rentmycar.rent.service.RentCarListService;
import com.rentmycar.rentmycar.rent.service.RentCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1.0/rent/")
public class RentController {
    private final RentCarService rentCarService;
    private final RentCarListService rentCarListService;
    private final UserService userService;


    @Autowired
    public RentController(RentCarService rentCarService, RentCarListService rentCarListService, UserService userService) {
        this.rentCarService = rentCarService;
        this.rentCarListService = rentCarListService;
        this.userService = userService;
    }

    @GetMapping(path="list/")
    public List<RentCarListDto> cars() {
        User user = userService.getAuthenticatedUser();
        return rentCarListService.getCarList();
    }

    @GetMapping(path="{id}/")
    public RentCarDto getCar(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return rentCarService.getCarById(id);
    }


}
