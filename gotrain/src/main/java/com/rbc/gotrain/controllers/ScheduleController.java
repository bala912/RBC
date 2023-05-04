package com.rbc.gotrain.controllers;

import com.rbc.gotrain.models.Train;
import com.rbc.gotrain.services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule")
    public List<Train> Test() {
        return scheduleService.getAll();
    }
}
