package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.Timeslot;
import com.rentmycar.rentmycar.service.TimeslotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1.0/timeslot/")
public class TimeslotController {

    private final TimeslotService timeslotService;

    public TimeslotController(TimeslotService timeslotService) {
        this.timeslotService = timeslotService;
    }

    @GetMapping
    public List<Timeslot> getTimeslots() {
        return timeslotService.getTimeslots();
    }
}
