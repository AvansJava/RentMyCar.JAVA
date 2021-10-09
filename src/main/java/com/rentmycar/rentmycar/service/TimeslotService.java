package com.rentmycar.rentmycar.service;

import com.rentmycar.rentmycar.model.Timeslot;
import com.rentmycar.rentmycar.repository.TimeslotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeslotService {

    private final TimeslotRepository timeslotRepository;

    public TimeslotService(TimeslotRepository timeslotRepository) {
        this.timeslotRepository = timeslotRepository;
    }

    public List<Timeslot> getTimeslots() {
        return timeslotRepository.findAll();
    }
}
