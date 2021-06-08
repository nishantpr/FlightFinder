package com.np.flightfinder.domain.algortihm;

import com.np.flightfinder.exception.FlightsNotFoundException;
import com.np.flightfinder.model.Flight;
import com.np.flightfinder.domain.FlightGraph;
import com.np.flightfinder.domain.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BFS implements KShortestPathAlgo{

    @Value("${flight.min-layover}")
    int minLayover;

    @Value("${flight.max-layover}")
    int maxLayover;

    public List<Path> findKShortestPaths(FlightGraph graph, String source, String destination, int k) {

        PriorityQueue<Path> paths = new PriorityQueue<Path>();

        Path start = new Path(source);
        List<Flight> flightsFromSource = graph.getFlights(source);
        Queue<Path> pathsQ = new LinkedList<Path>();
        for(Flight flight: flightsFromSource){
            Path next = start.getCopy();
            next.addFlight(flight);
            pathsQ.offer(next);
        }

        while(!pathsQ.isEmpty()){
            Path current = pathsQ.poll();
            String currentCity = current.getDestination();
            Flight currentFlight = current.getLastFlight();

            //final destination reached
            if(currentCity.equals(destination)){
                paths.offer(current);
                continue;
            }

            List<Flight> flightsFromCurrent = graph.getFlights(currentCity);
            for(Flight flight: flightsFromCurrent){

                //avoid loop
                if(current.getCitySet().contains(flight.getTo())){
                    continue;
                }

                int layover = currentFlight.getEndTime().getDuration(flight.getStartTime());
                //setting maximum layover time to minimize processing time.
                if(layover < minLayover || layover > maxLayover){
                    continue;
                }

                Path next = current.getCopy().addFlight(flight);
                if(paths.size() > k){
                    continue;
                }
                pathsQ.offer(next);
            }
        }

        //preparing result list
        LinkedList<Path> result = new LinkedList<>();
        HashMap<String, Integer> layoverPathShortestDuration = new HashMap<>();

        int nonStopIndex = 0;
        int currentSize = 0;
        while(currentSize < k){
            if(paths.isEmpty()){
                break;
            }

            Path p = paths.poll();

            //layover path of shorter duration already present
            if(layoverPathShortestDuration.containsKey(p.getCities()) && p.getDuration() > layoverPathShortestDuration.get(p.getCities())){
                continue;
            }

            //checking if non stop or layover flight and adding in appropriate position
            if(p.getFlightPath().size() == 1){
                result.add(nonStopIndex, p);
                nonStopIndex++;
            } else {
                layoverPathShortestDuration.put(p.getCities(), p.getDuration());
                result.addLast(p);
            }
            currentSize++;
        }

        if(result.isEmpty()){
            throw new FlightsNotFoundException("No flights found between the given 2 cities");
        }

        return result;
    }

}
