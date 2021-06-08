package com.np.flightfinder.domain;

import com.np.flightfinder.domain.FlightGraph;
import com.np.flightfinder.model.Flight;
import com.np.flightfinder.model.Time;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class FlightSchedule {

	@Value("${flight.schedule.path}")
	String flightSchedulePath;

	FlightGraph graph = new FlightGraph();

	@PostConstruct
	public void readSchedule() throws IOException {
		File file = new File(flightSchedulePath);
		FileReader filereader = new FileReader(file);
		CSVReader csvReader = new CSVReader(filereader);

		String [] nextRow;
		while((nextRow = csvReader.readNext()) != null) {
			addFlight(nextRow[0].trim(), nextRow[1], nextRow[2], nextRow[3], nextRow[4]);
		}
	}

	public void addFlight(String flightNumber, String from, String to, String startTime, String endTime){
	    System.out.println(flightNumber+"-"+from+"-"+to+"-"+startTime+"-"+endTime);

        try {
            Flight f = new Flight(flightNumber, from, to, new Time(startTime), new Time(endTime));
			graph.addCity(from);
			graph.addCity(to);
            graph.addFlight(from, f);
        } catch (Exception e){
            System.out.println(flightNumber+"-"+from+"-"+to+"-"+startTime+"-"+endTime);
        }
	}

	public FlightGraph getGraph() {
		return graph;
	}
}
