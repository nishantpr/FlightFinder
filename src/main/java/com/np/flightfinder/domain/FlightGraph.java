package com.np.flightfinder.domain;

import com.np.flightfinder.model.Flight;

import java.util.*;

public class FlightGraph implements Cloneable{

    HashMap<String, List<Flight>> adjList = new HashMap<>();

    public void addCity(String city){
        if(!adjList.containsKey(city)){
            adjList.put(city, new ArrayList<>());
        }
    }

    public void addFlight(String source, Flight f){
        if(adjList.containsKey(source)){
            adjList.get(source).add(f);
        } else {
            List<Flight> flights = new ArrayList<>();
            flights.add(f);
            adjList.put(source, flights);
        }
    }

    public List<Flight> getFlights(String source){
        if(adjList.containsKey(source)){
            return adjList.get(source);
        }
        return new ArrayList<>();
    }

    public Set<String> getSetOfCities(){
        return adjList.keySet();
    }


    HashMap<String, List<Flight>> removedCities = new HashMap<>();
    public void temporaryRemoveCity(String city){
        if(adjList.containsKey(city)){
            removedCities.put(city, adjList.get(city));
            adjList.remove(city);
        }
    }

    public void addRemovedCities(){
        adjList.putAll(removedCities);
        removedCities.clear();
    }

    HashMap<String, List<Flight>> removedFlights = new HashMap<>();
    void temporaryRemoveFlight(String city, Flight flight){
        if(!adjList.containsKey(city)){
            return;
        }

        adjList.get(city).remove(flight);

        if(!removedFlights.containsKey(city)){
            removedFlights.put(city, new ArrayList<>());
        }
        removedFlights.get(city).add(flight);
    }

    void addRemovedFlights(){
        for(Map.Entry<String, List<Flight>> entry : removedFlights.entrySet()){
            if(adjList.containsKey(entry.getKey())){
                adjList.get(entry.getKey()).addAll(entry.getValue());
            } else {
                adjList.put(entry.getKey(), entry.getValue());
            }
        }
        removedFlights.clear();
    }

    public FlightGraph getCopy(){
        try {
            return (FlightGraph)this.clone();
        } catch(Exception e){
            return this;
        }
    }

}
