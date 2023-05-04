package com.rbc.gotrain;

import com.rbc.gotrain.utils.TimeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootTest
class GotrainApplicationTests {

	@Autowired
	private TimeConverter timeConverter;
	@Test
	void contextLoads() {
	}

	@Test
	void test12HourConversion() {
		String time = "11:00am";
		LocalTime localTime = timeConverter.from12HourFormat(time);
		System.out.println("12 hour LocalTime ==========>  " +  localTime);
	}

	@Test
	void test24HourConversion() {
		String time = "7";
		LocalTime localTime = timeConverter.from24HourFormat(time);
		System.out.println("24 hour LocalTime ==========>  " +  localTime);

	}



}
