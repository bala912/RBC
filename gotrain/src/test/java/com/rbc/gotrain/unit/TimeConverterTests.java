package com.rbc.gotrain.unit;

import com.rbc.gotrain.utils.TimeConverter;
import exceptions.InvalidTimeFormatException;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

/**
 *   24-Hour Time in Single, Three, Four Digits
 *   Valid Example: 700, 1215, 1755
 *   Invalid Example: 07
 *
 *   12 Hour Time Separated by colon
 *   Valid Example: 12:00am, 3:40pm, 7:55am
 *   Invalid Example: 6am, 11:00
 */
public class TimeConverterTests {

    private static TimeConverter timeConverter;


    @BeforeAll
    static void setUp() {
        timeConverter = new TimeConverter();
    }

    @Test
    void testValid12HourConversion() {
        String time = "7:05am";
        LocalTime localTime = timeConverter.convertToTime(time);
        LocalTime constructed = LocalTime.of(7,5,0); // 7:05 am in 24 hour format
        assertEquals(localTime,constructed, "Parsed time and Constructed time equal 7:05 am");

        time = "11:30am";
        localTime = timeConverter.convertToTime(time);
        constructed = LocalTime.of(11,30,0); // 11:30 am in 24 hour format
        assertEquals(localTime,constructed, "Parsed time and Constructed time equal 11:30 am");

        time = "3:40pm";
        localTime = timeConverter.convertToTime(time);
        constructed = LocalTime.of(15,40,0); // 3:40 pm in 24 hour format
        assertEquals(localTime,constructed, "Parsed time and Constructed time equal 3:40 pm");
    }

    @Test
    void testInvalid12HourConversion() {
        final String time = "6am";
        assertThrows(InvalidTimeFormatException.class, () -> timeConverter.convertToTime(time));

        final String time2 = "11:00";
        assertThrows(InvalidTimeFormatException.class, () -> timeConverter.convertToTime(time2));
    }

    @Test
    void testValid24HourConversion() {
        String time = "7"; // Single digit format
        LocalTime localTime = timeConverter.convertToTime(time);
        LocalTime constructed = LocalTime.of(7,0,0); // 7:00 am in 24 hour format
        assertEquals(localTime,constructed, "Parsed time and Constructed time equal 7:00 am");

        time = "700"; // Three digit format
        localTime = timeConverter.convertToTime(time);
        constructed = LocalTime.of(7,0,0); // 7 am in 24 hour format
        assertEquals(localTime,constructed, "Testing 3 digit, Parsed time and Constructed time equal 7:00 am");

        time = "1755";
        localTime = timeConverter.convertToTime(time);
        constructed = LocalTime.of(17,55,0); // 5:55 pm in 24 hour format
        assertEquals(localTime,constructed, "Parsed time and Constructed time equal 5:55 pm");

        time = "0015";
        localTime = timeConverter.convertToTime(time);
        constructed = LocalTime.of(0,15,0); // 12:15 am in 24 hour format
        assertEquals(localTime,constructed, "Parsed time and Constructed time equal 12:15 am");

    }

    @Test
    void testInvalid24HourConversion() {
        final String time = "07";
        assertThrows(InvalidTimeFormatException.class, () -> timeConverter.convertToTime(time));

        final String time2 = "09000";
        assertThrows(InvalidTimeFormatException.class, () -> timeConverter.convertToTime(time2));
    }


}
