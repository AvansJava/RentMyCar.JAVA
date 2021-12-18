package com.rentmycar.rentmycar.rent.controller;

import com.rentmycar.rentmycar.dto.CarDto;
import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.rent.dto.RentCarAvailabilityDto;
import com.rentmycar.rentmycar.rent.dto.RentCarDto;
import com.rentmycar.rentmycar.rent.dto.RentCarListDto;
import com.rentmycar.rentmycar.rent.service.RentCarFilter;
import com.rentmycar.rentmycar.rent.service.RentCarListService;
import com.rentmycar.rentmycar.rent.service.RentCarService;
import com.rentmycar.rentmycar.service.CarTimeslotAvailabilityService;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1.0/rent/")
public class RentController {
    private final RentCarService rentCarService;
    private final RentCarFilter rentCarFilter;
    private final RentCarListService rentCarListService;
    private final UserService userService;


    @Autowired
    public RentController(RentCarService rentCarService, RentCarListService rentCarListService, RentCarFilter rentCarFilter,
                          UserService userService, CarTimeslotAvailabilityService carTimeslotAvailabilityService) {
        this.rentCarService = rentCarService;
        this.rentCarFilter = rentCarFilter;
        this.rentCarListService = rentCarListService;
//        this.carTimeslotAvailabilityService = carTimeslotAvailabilityService;
        this.userService = userService;
    }

    @GetMapping(path="list/")
    public List<CarDto> cars() {
        User user = userService.getAuthenticatedUser();
        return rentCarListService.getCarList();
    }

    @GetMapping(path="list/filter/")
    public List<CarDto> getFilteredCars(@RequestParam(required=false) String startDate,
                                        @RequestParam(required=false) String  endDate,
                                        @RequestParam(required=false) String  brand) {
        return rentCarFilter.getFilteredCars(LocalDate.parse(startDate),
                                             LocalDate.parse(endDate),
                                             brand);
    }

    @GetMapping(path="{id}/")
    public CarDto getCar(@PathVariable("id") Long id) {
        User user = userService.getAuthenticatedUser();
        return rentCarService.getCarById(id);
    }

    @GetMapping(path="{id}/availability/")
    public Page<RentCarAvailabilityDto> getCarAvailability(
            @PathVariable("id") Long id,
            @RequestParam int pageNumber,
            @RequestParam int pageSize
    ) {
        User user = userService.getAuthenticatedUser();
        return rentCarService.getCarAvailability(id, pageNumber, pageSize);
    }

}
