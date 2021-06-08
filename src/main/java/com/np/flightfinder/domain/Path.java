package com.np.flightfinder.domain;

import com.np.flightfinder.model.Flight;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@ToString
public class Path implements Comparable<Path>{

    List<Flight> flightPath = new ArrayList<>();
    HashSet<String> citySet = new HashSet<>();
    int duration = 0;
    String cities;
    String source;

    public Path(String source){
        this.source = source;
        cities = source;
        citySet.add(source);
    }

    public Path addFlight(Flight flight){
        flightPath.add(flight);
        citySet.add(flight.getTo());
        cities = cities+"_"+flight.getTo();
        if(flightPath.size() == 1){
            duration = flight.getDuration();
        } else {
            duration += flightPath.get(flightPath.size()-2).getEndTime().getDuration(flight.getEndTime());
        }
        return this;
    }

    public Path getCopy(){
        Path p = new Path(this.source);
        for(Flight flight: flightPath){
            p.addFlight(flight);
        }
        return p;
    }

    public String getDestination(){
        return flightPath.get(flightPath.size()-1).getTo();
    }

    public Flight getLastFlight() { return flightPath.get(flightPath.size()-1); }

    @Override
    public int compareTo(Path p) {
        if(this.duration == p.duration){
            return flightPath.size() - p.flightPath.size();
        }
        return this.duration - p.duration;
    }
}
