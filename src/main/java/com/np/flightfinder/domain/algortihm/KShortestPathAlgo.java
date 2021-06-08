package com.np.flightfinder.domain.algortihm;

import com.np.flightfinder.domain.FlightGraph;
import com.np.flightfinder.domain.Path;

import java.util.List;

public interface KShortestPathAlgo {
    public List<Path> findKShortestPaths(FlightGraph graph, String source, String destination, int k);
}
