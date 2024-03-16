package com.example.geektrust.services;

import java.util.ArrayList;
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

    // Taking List of PassengerTypes and StationTypes
    List<PassangerType> passangerTypes = new ArrayList<>();
    List<StationType> stationTypes = new ArrayList<>();

    // Available From Stations
    List<List<Journey>> fromStations = new ArrayList<>();
    List<CollectionSummary> collectionSummarys = new ArrayList<>();

    Map<StationType, Map<PassangerType, PassengerSummary>> mapOfPassengerSummaries = new HashMap<>();

    public SummaryService(IJourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @Override
    public void buildSummary() {
        for (StationType stationType : stationTypes) {
            List<Journey> journeys = journeyService.getTripsFrom(stationType);
            if (journeys == null) {
                continue; // Skip to the next station if no journeys are available
            }
            processJourneys(stationType, journeys);
        }
    }

    private void processJourneys(StationType station, List<Journey> journeys) {
        CollectionSummary collectionSummary = new CollectionSummary(station);
        Map<PassangerType, PassengerSummary> passengerSummaryMap = new HashMap<>();

        for (Journey journey : journeys) {
            updateCollectionSummary(collectionSummary, journey);
            updatePassengerSummary(passengerSummaryMap, journey);
        }

        collectionSummarys.add(collectionSummary);
        mapOfPassengerSummaries.put(station, passengerSummaryMap);
    }

    private void updateCollectionSummary(CollectionSummary collectionSummary, Journey journey) {
        collectionSummary.addToCollection(journey.getFare() - journey.getDiscount());
        collectionSummary.addToCollection(journey.getCharges());
        collectionSummary.addToDiscount(journey.getDiscount());
    }

    private void updatePassengerSummary(Map<PassangerType, PassengerSummary> passengerSummaryMap, Journey journey) {
        PassangerType passengerType = journey.getPassengerType();
        passengerSummaryMap.compute(passengerType, (key, summary) -> {
            if (summary == null) {
                return new PassengerSummary(passengerType, 1);
            } else {
                summary.addCount();
                return summary;
            }
        });
    }

    // TOTAL_COLLECTION CENTRAL 300 0
    // PASSENGER_TYPE_SUMMARY
    // ADULT 1
    // SENIOR_CITIZEN 1
    // TOTAL_COLLECTION AIRPORT 403 100
    // PASSENGER_TYPE_SUMMARY
    // ADULT 2
    // KID 2

    // throw new UnsupportedOperationException("Unimplemented method
    // 'buildSummary'");

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

            answer += "TOTAL_COLLECTION " + station.toString() + " " + (int) summary.getTotalCollection() + " "
                    + (int) summary.getTotalDiscount() + "\n";
            answer += "PASSENGER_TYPE_SUMMARY\n";

            for (PassengerSummary passengerSummary : passengerSummaries) {
                answer += passengerSummary.getPassenger() + " " + passengerSummary.getCount() + "\n";
            }

        }

        System.out.println(answer);

    }

    public void setTypes(List<PassangerType> passangerTypes, List<StationType> stationTypes) {
        this.passangerTypes = passangerTypes;
        this.stationTypes = stationTypes;
    }

    public List<CollectionSummary> getCollectionSummary() {

        return collectionSummarys;

    }

    public Map<StationType, Map<PassangerType, PassengerSummary>> getPassengerSummary() {
        return mapOfPassengerSummaries;
    }

    public void setCollectionSummarys(List<CollectionSummary> collectionSummaries) {
        this.collectionSummarys = collectionSummaries;
    }

    public void setPassengerSummary(Map<StationType, Map<PassangerType, PassengerSummary>> passengerSummaries) {
        this.mapOfPassengerSummaries = passengerSummaries;
    }

}
