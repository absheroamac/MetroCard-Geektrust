package com.example.geektrust.services;

import java.util.List;
import java.util.Map;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.utils.CollectionSummary;
import com.example.geektrust.utils.PassengerSummary;

public interface IJourneyService {

    public void createJourney(String id, Passanger passanger, Station from);

    public CollectionSummary getStationSummary(StationType station);

    public List<Journey> getJourneysOf(String id);

    public Map<PassangerType, PassengerSummary> getPassengerSummary(StationType station);

    public Map<StationType, CollectionSummary> getCollectionSummaries();

    // public List<Journey> getTripsOf(PassangerType passangerType);

}
