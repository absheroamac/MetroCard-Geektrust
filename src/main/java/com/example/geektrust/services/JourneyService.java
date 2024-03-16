package com.example.geektrust.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;

public class JourneyService implements IJourneyService {

    Map<String, Journey> journeys = new HashMap<>();
    Map<String, List<Journey>> journeysMap = new HashMap<>();
    // Map<PassangerType, List<Journey>> passangerJourneyMap = new HashMap<>();
    // Map<StationType, Double> collectedFees = new HashMap<>();
    Map<StationType, List<Journey>> journeysFromMap = new HashMap<>();

    @Override
    public Journey createJourney(MetroCard metrocard, Passanger passanger, Station from) {
        // String id = metrocard.getId();

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
            List<Journey> arrayList = new ArrayList<>();
            arrayList.add(journey);
            journeysMap.put(id, arrayList);
        }

        if (journeysFromMap.containsKey(journey.getFrom())) {
            List<Journey> current = journeysFromMap.getOrDefault(journey.getFrom(), new ArrayList<>());
            current.add(journey);
            journeysFromMap.put(journey.getFrom(), current);

        }

        else {
            List<Journey> arrayList = new ArrayList<>();
            arrayList.add(journey);
            journeysFromMap.put(journey.getFrom(), arrayList);
        }
        return journey;
    }

    @Override
    public List<Journey> getTripsFrom(StationType station) {
        return journeysFromMap.get(station);
    }

    public List<Journey> getJourneysOf(String id) {
        List<Journey> defult = new ArrayList<>();
        return journeysMap.getOrDefault(id, defult);
    }

    // public Map<StationType, Double> getCharges() {
    // return this.collectedFees;
    // }

    public void setJourneys(Map<String, Journey> journeys) {
        this.journeys = journeys;
    }

    public void setJourneysMap(Map<StationType, List<Journey>> journeysMap) {
        this.journeysFromMap = journeysMap;
    }

}
