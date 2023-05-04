package com.rbc.gotrain.services;

import com.rbc.gotrain.models.Train;
import com.rbc.gotrain.repositories.TrainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final TrainRepository trainRepository;

    public List<Train> getAll(){
        return trainRepository.findAll();
    }
}
