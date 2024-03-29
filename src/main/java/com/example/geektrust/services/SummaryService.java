package com.example.geektrust.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.utils.CollectionSummary;
import com.example.geektrust.utils.PassengerSummary;

public class SummaryService implements ISummaryService {

    IJourneyService journeyService;
    Map<StationType, List<PassengerSummary>> passengerSummaries;
    Map<StationType, CollectionSummary> collectionSummaries;

    public SummaryService(IJourneyService journeyService) {
        this.journeyService = journeyService;
        passengerSummaries = new HashMap<>();
    }

    @Override
    public void buildSummary() {

        collectionSummaries = journeyService.getCollectionSummaries();

        setSummary(StationType.CENTRAL);
        setSummary(StationType.AIRPORT);
    }

    public void setSummary(StationType type) {
        Map<PassangerType, PassengerSummary> map = journeyService.getPassengerSummary(type);
        List<PassengerSummary> current = new ArrayList<>(map.values());
        Collections.sort(current);
        passengerSummaries.put(type, current);

    }

    @Override
    public void printSummary() {

        String answer = "";
        answer = getSummary(answer, StationType.CENTRAL);
        answer = getSummary(answer, StationType.AIRPORT);

        System.out.println(answer);

    }

    public String getSummary(String answer, StationType stationType) {

        CollectionSummary collectionSummary = collectionSummaries.get(stationType);
        answer += "TOTAL_COLLECTION " + collectionSummary.getStation() + " "
                + collectionSummary.getTotalCollection() + " " + collectionSummary.getTotalDiscount() + "\n";
        answer += "PASSENGER_TYPE_SUMMARY\n";

        List<PassengerSummary> currentPassengers = passengerSummaries.get(stationType);
        for (PassengerSummary passengerSummary : currentPassengers) {
            answer += passengerSummary.getPassenger() + " " + passengerSummary.getCount() + "\n";
        }

        return answer;

    }

    public Map<StationType, List<PassengerSummary>> getPassengerSummary() {
        return this.passengerSummaries;
    }

    public Map<StationType, CollectionSummary> getCollectionSummary() {
        return this.collectionSummaries;
    }

    public void setPassengerSummary(Map<StationType, List<PassengerSummary>> passengerSummaries) {
        this.passengerSummaries = passengerSummaries;
    }

    public void setCollectionSummarys(Map<StationType, CollectionSummary> collectionSummaries) {
        this.collectionSummaries = collectionSummaries;
    }

}
