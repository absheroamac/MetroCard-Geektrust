package com.example.geektrust.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;

public class JourneyService implements IJourneyService {

    Map<String, Journey> journeys;
    Map<String, List<Journey>> journeysMap = new HashMap<>();
    // Map<PassangerType, List<Journey>> passangerJourneyMap = new HashMap<>();
    // Map<StationType, Double> collectedFees = new HashMap<>();
    Map<StationType, List<Journey>> journeysFromMap = new HashMap<>();

    @Override
    public Journey createJourney(MetroCard metrocard, Passanger passanger, Station from) {
        // String id = metrocard.getId();

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createJourney'");
    }

    public Journey createJourney(MetroCard metroCard, Passanger passanger, Station from, double fare, double discount,
            double charges) {
        String id = metroCard.getId();
        StationType to;
        if (from.getStationType() == StationType.AIRPORT) {

            to = StationType.CENTRAL;

        } else {
            to = StationType.AIRPORT;
        }

        Station toStation = new Station(to);
        Journey journey = new Journey(passanger, from, toStation, fare, discount, charges);

        if (journeysMap.containsKey(id)) {
            List<Journey> current = journeysMap.get(id);
            current.add(journey);
            journeysMap.put(id, current);
        } else {
            journeysMap.put(id, Arrays.asList(journey));
        }

        if (journeysFromMap.containsKey(journey.getFrom())) {
            List<Journey> current = journeysFromMap.get(journey.getFrom());
            current.add(journey);
            journeysFromMap.put(journey.getFrom(), current);
        }

        else {
            journeysFromMap.put(journey.getFrom(), Arrays.asList(journey));
        }
        return journey;
    }

    @Override
    public List<Journey> getTripsFrom(StationType station) {
        return journeysFromMap.getOrDefault(station, Collections.EMPTY_LIST);
    }

    public List<Journey> getJourneysOf(String id) {
        return journeysMap.getOrDefault(id, Collections.EMPTY_LIST);
    }

    // public Map<StationType, Double> getCharges() {
    // return this.collectedFees;
    // }

    public void setJourneys(Map<String, Journey> journeys) {

        this.journeys = journeys;

    }

    public void setJourneysMap(Map<String, List<Journey>> journeysMap) {
        this.journeysMap = journeysMap;
    }

}
