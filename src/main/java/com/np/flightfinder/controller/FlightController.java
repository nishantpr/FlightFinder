package com.np.flightfinder.controller;

import com.np.flightfinder.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	FlightSearchService flightSearchService;

	@GetMapping("/{from}/{to}/{numberOfOptions}")
    @ResponseStatus(HttpStatus.OK)
	String findFlights(@PathVariable String from, @PathVariable String to, @PathVariable int numberOfOptions){
	    return flightSearchService
				.search(from, to, numberOfOptions)
				.toString();
	}

}
