package com.rbc.gotrain.integration;

import com.rbc.gotrain.controllers.GlobalExceptionHandler;
import com.rbc.gotrain.models.Train;
import com.rbc.gotrain.utils.TimeConverter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GotrainApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final int TOTAL_NUM_TRAINS = 17;

	@Autowired
	private TestRestTemplate testRestTemplate;

	/**
	 *  Get all Train Schedule; Total trains = 17
	 */
	@Test
	public void testGetAllSchedule() {
		ParameterizedTypeReference<List<Train>> responseType =  new ParameterizedTypeReference<>() {};
		String endPoint = "/schedule";
		ResponseEntity<List<Train>> response = testRestTemplate.exchange(endPoint, HttpMethod.GET,null,responseType);

		List<Train> trains = response.getBody();
		assertEquals(TOTAL_NUM_TRAINS, trains.size());
	}

	/**
	 *  Get all Train Schedule for Kitchener, test size and if all trains have Kitchener as line property
	 */
	@Test
	public void testGetTrainsByLine() {
		final String line = "Kitchener";
		final int NUM_KITCHENER_TRAINS = 5;
		ParameterizedTypeReference<List<Train>> responseType =  new ParameterizedTypeReference<>() {};
		String endPoint = "/schedule/" + line;

		ResponseEntity<List<Train>> response = testRestTemplate.exchange(endPoint, HttpMethod.GET,null,responseType);
		List<Train> trains = response.getBody();

		//Assert that there are 5 Kitchener trains
		assertEquals(NUM_KITCHENER_TRAINS, trains.size());

		//Test if Each Train has Kitchener as line property
		trains.forEach( (train -> {
			assertEquals(train.getLine().toLowerCase(), line.toLowerCase());
		}));

	}

	/**
	 *  404 Not found if Train line does not exist
	 */
	@Test
	public void testGetTrains404(){
		final String line = "Niagara";
		String endPoint = "/schedule/" + line;
		ResponseEntity<String> response = testRestTemplate.exchange(endPoint,HttpMethod.GET,null,String.class);

		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}

	/**
	 *  Get Train Schedule for line with departure time parameter 24-hour format
	 */
	@Test
	public void testGetTrainsByLineAndDeparture24Hour(){
		ParameterizedTypeReference<List<Train>> responseType =  new ParameterizedTypeReference<>() {};

		// TEST for 24 hour time departure time parameter
		String line = "Barrie";
		String departureTime = "1830";
		String endPoint = String.format("/schedule/%s?departure=%s",line,departureTime);
		ResponseEntity<List<Train>> response = testRestTemplate.exchange(endPoint, HttpMethod.GET,null,responseType);
		List<Train> trains = response.getBody();
		Train train= trains.get(0);

		assertEquals(trains.size(),1); // Array containing only one element
		assertEquals(train.getLine().toLowerCase(),line.toLowerCase()); // Check if line property is Barrie
		assertEquals(Integer.toString(train.getDeparture()).toLowerCase(),departureTime.toLowerCase()); //Check if Departure time matches

	}

	/**
	 *  Get Train Schedule line for Kitchener for departure time 2:15pm, Departure time in 12 hour format
	 */
	@Test
	public void testGetTrainsByLineAndDeparture12Hour(){
		ParameterizedTypeReference<List<Train>> responseType =  new ParameterizedTypeReference<>() {};

		// TEST for 24 hour time departure time parameter
		String line = "Kitchener";
		String departureTime = "2:15pm";
		String endPoint = String.format("/schedule/%s?departure=%s",line,departureTime);
		ResponseEntity<List<Train>> response = testRestTemplate.exchange(endPoint, HttpMethod.GET,null,responseType);
		List<Train> trains = response.getBody();
		Train train= trains.get(0);


		assertEquals(trains.size(),1); // Array containing only one element
		assertEquals(train.getLine().toLowerCase(),line.toLowerCase()); // Check if line property is Barrie
		assertEquals(Integer.toString(train.getDeparture()).toLowerCase(),"1415"); //Check if Departure time matches in 24 hour format

	}

	/**
	 *  Test for 400 Bad Request if Departure time is of invalid format
	 */
	@Test
	public void testBadRequestForInvalidDepartureTime(){
		final String line = "Kitchener";
		final String departureTime = "11";
		String endPoint = String.format("/schedule/%s?departure=%s",line,departureTime);
		ResponseEntity<String> response = testRestTemplate.exchange(endPoint,HttpMethod.GET,null,String.class);

		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	/**
	 *  Test if response is empty Array if no train schedule exists for line at given departure time
	 */
	@Test
	public void testEmptyArrayIfTrainDoesNotExist(){
		ParameterizedTypeReference<List<Train>> responseType =  new ParameterizedTypeReference<>() {};

		// TEST for 24 hour time departure time parameter
		String line = "Kitchener";
		String departureTime = "9:15pm";
		String endPoint = String.format("/schedule/%s?departure=%s",line,departureTime);
		ResponseEntity<List<Train>> response = testRestTemplate.exchange(endPoint, HttpMethod.GET,null,responseType);
		List<Train> trains = response.getBody();

		assertEquals(trains.size(),0); // Array containing only one element
	}





}
