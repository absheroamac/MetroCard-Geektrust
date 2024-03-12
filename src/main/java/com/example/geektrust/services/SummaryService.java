package com.example.geektrust.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.utils.CollectionSummary;
import com.example.geektrust.utils.PassengerSummary;

public class SummaryService implements ISummaryService {

    IJourneyService journeyService;
    List<PassangerType> passangerTypes = new ArrayList<>();
    List<StationType> stationTypes = new ArrayList<>();

    List<List<Journey>> fromStations = new ArrayList<>();
    List<CollectionSummary> collectionSummarys = new ArrayList<>();

    Map<StationType, Map<PassangerType, PassengerSummary>> mapOfPassengerSummaries = new HashMap<>();

    public SummaryService(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @Override
    public void buildSummary() {
        // TODO Auto-generated method stub

        // List<PassengerSummary> passengersFromCentral = new ArrayList<>();
        // List<PassengerSummary> passengersFromAirport = new ArrayList<>();

        
        // List<Journey> fromCentral = journeyService.getTripsFrom(StationType.CENTRAL);
        // List<Journey> fromAirport = journeyService.getTripsFrom(StationType.AIRPORT);

        // CollectionSummary centralSummary = new CollectionSummary(StationType.CENTRAL);
        // CollectionSummary airPortSummary = new CollectionSummary(StationType.AIRPORT);

        for(StationType stationType:stationTypes){
            fromStations.add(journeyService.getTripsFrom(stationType));
        }

        for(List<Journey> journeys:fromStations){//single station

            StationType station = journeys.get(0).getFrom();

            CollectionSummary collectionSummary = new CollectionSummary(station);
            mapOfPassengerSummaries.put(station,new HashMap<>());
            Map<PassangerType,PassengerSummary> map = mapOfPassengerSummaries.get(station)



            for(Journey journey:journeys){
                collectionSummary.addToCollection(journey.getFare());
                collectionSummary.addToCollection(journey.getCharges());
                collectionSummary.addToDiscount(journey.getDiscount());
                PassangerType passangerType = journey.getPassengerType();
                if(map.containsKey(passangerType)){
                    PassengerSummary current = map.get(passangerType);
                    current.addCount();
                    map.put(passangerType, current);
                }
                else{
                    map.put(passangerType,new PassengerSummary(passangerType, 1));
                }
                

            }

            mapOfPassengerSummaries.put(station,map);

        }

        // TOTAL_COLLECTION CENTRAL 300 0 
        // PASSENGER_TYPE_SUMMARY
        // ADULT 1
        // SENIOR_CITIZEN 1
        // TOTAL_COLLECTION AIRPORT  403 100 
        // PASSENGER_TYPE_SUMMARY
        // ADULT 2
        // KID 2 
        
        //throw new UnsupportedOperationException("Unimplemented method 'buildSummary'");
    }

    @Override
    public void printSummary() {

        String answer = "";

        for (int i = 0; i < collectionSummarys.size(); i++) {

            CollectionSummary summary = collectionSummarys.get(i);
            StationType station = summary.getStation();
            Map<PassangerType, PassengerSummary> passengerSummaryMap = mapOfPassengerSummaries.get(station);
            List<PassengerSummary> passengerSummaries = new ArrayList<>();
            for (PassangerType passengertype : passengerSummaryMap.keySet()) {

                passengerSummaries.add(passengerSummaryMap.get(passengertype));

            }
            Collections.sort(passengerSummaries);

            answer += "TOTAL_COLLECTION " + station.toString() + " " + summary.getTotalCollection();

        }

    }

}
