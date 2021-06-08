package com.np.flightfinder.service;

import com.np.flightfinder.dto.PathsDto;

public interface FlightSearchService {

    public PathsDto search(String from, String to, int numberOfOptions);

}
