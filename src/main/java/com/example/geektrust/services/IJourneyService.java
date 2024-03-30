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

    /**
     * Create Journey object based on the input
     * 
     * @param id        id of the metroCard
     * @param passanger Passenger Type
     * @param from      From Station type
     */
    public void createJourney(String id, Passanger passanger, Station from);

    /**
     * Get Station Summary
     * 
     * @param station Station type
     * @return Return list of StationSummary objects from the stationType
     */

    public CollectionSummary getStationSummary(StationType station);

    /**
     * Collect Journeys by card id
     * 
     * @param id id of the MetroCard
     * @return List of Journey objects of the metroCard id.
     */

    public List<Journey> getJourneysOf(String id);

    /**
     * collect Passenger Summarys
     * 
     * @param station Station type
     * @return Return Map of PassengerType and PassengerSummary objects
     */

    public Map<PassangerType, PassengerSummary> getPassengerSummary(StationType station);

    public Map<StationType, CollectionSummary> getCollectionSummaries();

    public void setJourneys(Map<String, List<Journey>> journey);

    public Map<String, List<Journey>> getJourneys();

    // public List<Journey> getTripsOf(PassangerType passangerType);

}
