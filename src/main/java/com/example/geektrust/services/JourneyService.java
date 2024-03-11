package com.example.geektrust.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.Station;

public class JourneyService implements IJourneyService {

    Map<String, Journey> journeys;
    Map<String, List<Journey>> journeysMap = new HashMap<>();

    @Override
    public Journey createJourney(MetroCard metrocard, Passanger passanger, Station from) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createJourney'");
    }

    @Override
    public List<Journey> getTripsFrom(Station station) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTripsFrom'");
    }

    public void setJourneys(Map<String, Journey> journeys) {

        this.journeys = journeys;

    }

    public void setJourneysMap(Map<String, List<Journey>> journeysMap) {
        this.journeysMap = journeysMap;
    }

}
