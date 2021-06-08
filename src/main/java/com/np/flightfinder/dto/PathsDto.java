package com.np.flightfinder.dto;

import com.google.gson.Gson;
import com.np.flightfinder.domain.Path;
import com.np.flightfinder.model.Flight;

import java.util.*;

public class PathsDto {

    List<Map<String, Map<String, Integer>>> paths = new ArrayList<>();
    public PathsDto(List<Path> pathList){
        for(Path p : pathList){
            List<Flight> flights = p.getFlightPath();
            StringBuilder sbCities = new StringBuilder(flights.get(0).getFrom());
            StringBuilder sbFlights = new StringBuilder();
            flights.forEach(f -> {
                sbCities.append("_"+f.getTo());
                sbFlights.append(f.getFlightNumber()+"_");
            });
            String key = sbCities.toString();
            HashMap<String, Integer> value = new HashMap<>();
            value.put(sbFlights.substring(0, sbFlights.length()-1), p.getDuration());
            Map<String, Map<String, Integer>> map = new HashMap<>();
            map.put(key, value);
            paths.add(map);
        }
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(paths);
    }
}
