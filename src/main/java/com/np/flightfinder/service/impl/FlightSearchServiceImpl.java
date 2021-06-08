package com.np.flightfinder.service.impl;

import com.np.flightfinder.domain.algortihm.BFS;
import com.np.flightfinder.domain.Path;
import com.np.flightfinder.domain.FlightSchedule;
import com.np.flightfinder.domain.algortihm.KShortestPathAlgo;
import com.np.flightfinder.dto.PathsDto;
import com.np.flightfinder.exception.CityNotFoundException;
import com.np.flightfinder.service.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {

    @Autowired
    FlightSchedule flightSchedule;

    @Autowired
    KShortestPathAlgo bfs;

    public PathsDto search(String from, String to, int numberOfOptions) {
        if(checkIfInvalidCity(from) || checkIfInvalidCity(to)){
            throw new CityNotFoundException("Invalid city");
        }
        List<Path> paths = bfs.findKShortestPaths(flightSchedule.getGraph(), from, to, numberOfOptions);
        return new PathsDto(paths);
    }

    private boolean checkIfInvalidCity(String city){
        if(flightSchedule.getGraph().getSetOfCities().contains(city)){
            return false;
        } else {
            return true;
        }
    }

}
