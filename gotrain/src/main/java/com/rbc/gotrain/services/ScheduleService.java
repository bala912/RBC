package com.rbc.gotrain.services;

import com.rbc.gotrain.models.Train;
import com.rbc.gotrain.repositories.TrainRepository;
import com.rbc.gotrain.utils.TimeConverter;
import exceptions.NoTrainFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final TrainRepository trainRepository;
    private final TimeConverter timeConverter;

    public List<Train> getAll(){
        return trainRepository.findAll();
    }

    public List<Train> getTrainsByLineAndDeparture(String line,String departure) throws NoTrainFoundException {
        List<Train> trains = trainRepository.findAll()
                .stream().filter( train -> train.getLine().equalsIgnoreCase(line)).collect(Collectors.toList());

       if(trains.size() ==0 ){
           throw new NoTrainFoundException(String.format("No trains for line %s", line));
       }
       else if(departure != null) { // Filter by Departure time now
           LocalTime departureTime = timeConverter.convertToTime(departure);
           Optional<Train> result =  trains.stream().
                   filter(train -> departureTime.equals(timeConverter.convertToTime(Integer.toString(train.getDeparture()))))
                   .findFirst();

           return result.isPresent() ? Collections.singletonList(result.get()) : Collections.emptyList();
       }
       else
           return trains;
    }
}
