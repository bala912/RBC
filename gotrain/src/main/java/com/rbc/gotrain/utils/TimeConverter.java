package com.rbc.gotrain.utils;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class TimeConverter {

    private final String colon = ":";

    public LocalTime convertToTime(String timeInString) {

        LocalTime localTime = null;
        //Check if 12 hour or 24 hour format
        if(timeInString.contains(colon))
            localTime = from12HourFormat(timeInString);
        else
            localTime = from24HourFormat(timeInString);

        return localTime;
    }

    //TODO: Pattern logic works; Add comments; Change to private
    public LocalTime from24HourFormat(String timeinString) {
        String pattern = "";
        if(timeinString.length() == 1)
            pattern = "H";
        else if (timeinString.length() == 3)
            pattern = "Hmm";
        else
            pattern = "HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
        return LocalTime.parse(timeinString, formatter);
    }

    //TODO: Change this over to Private; Add for conversion
    public LocalTime from12HourFormat(String timeInString) {
        //Remove all extra spaces and convert am/pm to uppercase
        String input = timeInString.toUpperCase().trim().replace(" ","");
        System.out.println(input);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma", Locale.US);
        return LocalTime.parse(input,formatter);
    }
}
