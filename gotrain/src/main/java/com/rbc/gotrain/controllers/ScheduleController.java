package com.rbc.gotrain.controllers;

import com.rbc.gotrain.models.Train;
import com.rbc.gotrain.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);


    @GetMapping("/schedule")
    public List<Train> getAllTrainSchedule() {
        return scheduleService.getAll();
    }

    @GetMapping("/schedule/{line}")
    public List<Train> getTrainScheduleByLineAndDeparture(@PathVariable String line,
                                                          @RequestParam( required = false) String departure) {
        List<Train> trains =  scheduleService.getTrainsByLineAndDeparture(line,departure);

        return trains;
    }




}
