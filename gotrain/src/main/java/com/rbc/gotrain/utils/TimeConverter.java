package com.rbc.gotrain.utils;

import exceptions.InvalidTimeFormatException;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Component
public class TimeConverter {

    private final String colon = ":";

    /**
     *
     * @param timeInString 12 "hh:mma" or 24 hour "H","Hmm","HHmm" format
     * @return LocalTime Object in 24-hour format
     * @throws InvalidTimeFormatException if time is not of accepted valid 12 or 24 hour format
     */
    public LocalTime convertToTime(String timeInString) throws InvalidTimeFormatException {

        LocalTime localTime = null;
        //Check if 12 hour or 24-hour format
        try{
            if(timeInString.contains(colon))
                localTime = from12HourFormat(timeInString);
            else
                localTime = from24HourFormat(timeInString);
        }
        catch (DateTimeParseException ex) {
            String msg = String.format("Time String: %s is not of valid format",timeInString);
            throw new InvalidTimeFormatException(msg + "\n" + ex.getMessage());
        }


        return localTime;
    }

    /**
     *
     * @param timeinString 24 Hour time as String, single, three or four digit representation
     * @return LocalTime object in 24-hour format
     */
    private LocalTime from24HourFormat(String timeinString) {
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

    /**
     *
     * @param timeInString 12 hour time as String, hh:mma format
     * @return LocalTime object in 24-hour format
     */
    private LocalTime from12HourFormat(String timeInString) {
        //Remove all extra spaces and convert am/pm to uppercase
        String input = timeInString.toUpperCase().trim().replace(" ","");
        String pattern = "";
        //Add the case to check for the pattern
        String[] split = input.split(":");
        if(split[0].length() == 1)
            pattern = "h:mma";
        else
            pattern = "hh:mma";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.US);
        return LocalTime.parse(input,formatter);
    }
}
