package com.np.flightfinder.model;

import lombok.Data;

@Data
public class Flight {
	
	String flightNumber;
	String from;
	String to;
    Time startTime;
    Time endTime;
	int duration;

    public Flight(String flightNumber, String from, String to, Time startTime, Time endTime){
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.startTime = startTime;
        this.endTime = endTime;
        duration = startTime.getDuration(endTime);
    }

}
