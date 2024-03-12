package com.example.geektrust.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;

public class JourneyService implements IJourneyService {

    Map<String, Journey> journeys;
    Map<String, List<Journey>> journeysMap = new HashMap<>();
    Map<PassangerType, List<Journey>> passangerJourneyMap = new HashMap<>();
    Map<StationType, Double> collectedFees = new HashMap<>();

    @Override
    public Journey createJourney(MetroCard metrocard, Passanger passanger, Station from) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createJourney'");
    }

    @Override
    public List<Journey> getTripsFrom(StationType station) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTripsFrom'");
    }

    public List<Journey> getTripsOf(PassangerType passanger) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTripsFrom'");
    }

    public void addCharges(double charges) {

    }

    public Map<StationType, Double> getCharges() {
        return this.collectedFees;
    }

    public void setJourneys(Map<String, Journey> journeys) {

        this.journeys = journeys;

    }

    public void setJourneysMap(Map<String, List<Journey>> journeysMap) {
        this.journeysMap = journeysMap;
    }

    public void setPassengerJourneysMap(Map<PassangerType, List<Journey>> passangerJourneyMap) {
        this.passangerJourneyMap = passangerJourneyMap;
    }

    public void getCharges(Map<StationType, Double> charges) {
        this.collectedFees = charges;
    }

}
