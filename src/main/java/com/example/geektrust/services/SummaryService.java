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

        for (StationType type : StationType.values()) {

            Map<PassangerType, PassengerSummary> map = journeyService.getPassengerSummary(type);
            List<PassengerSummary> current = new ArrayList<>(map.values());
            Collections.sort(current);
            passengerSummaries.put(type, current);

        }
    }

    @Override
    public void printSummary() {

        String answer = "";
        for (StationType stationType : StationType.values()) {
            CollectionSummary collectionSummary = collectionSummaries.get(stationType);
            answer += "TOTAL_COLLECTION " + collectionSummary.getStation() + " "
                    + collectionSummary.getTotalCollection() + " " + collectionSummary.getTotalDiscount() + "\n";
            answer += "PASSENGER_TYPE_SUMMARY\n";

            List<PassengerSummary> currentPassengers = passengerSummaries.get(stationType);
            for (PassengerSummary passengerSummary : currentPassengers) {
                answer += passengerSummary.getPassenger() + " " + passengerSummary.getCount() + "\n";
            }
        }

        System.out.println(answer);

    }

}
