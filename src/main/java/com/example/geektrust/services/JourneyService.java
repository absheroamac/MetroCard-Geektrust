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
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.utils.Bill;
import com.example.geektrust.utils.CollectionSummary;
import com.example.geektrust.utils.PassengerSummary;
import com.example.geektrust.utils.RechargeSummary;

public class JourneyService implements IJourneyService {

    private Map<String, List<Journey>> journeysMap;
    private Map<StationType, CollectionSummary> stationSummary;
    private Map<StationType, Map<PassangerType, PassengerSummary>> passengerSummarys;
    private IMetroCardService metroCardService;
    private IFareCalculationService fareCalculationService;

    public JourneyService(IMetroCardService metroCardService, IFareCalculationService fareCalculationService) {
        this.metroCardService = metroCardService;
        this.fareCalculationService = fareCalculationService;
        journeysMap = new HashMap<>();
        stationSummary = new HashMap<>();
        passengerSummarys = new HashMap<>();

        for (StationType type : StationType.values()) {
            stationSummary.put(type, new CollectionSummary(type));
            passengerSummarys.put(type, new HashMap<>());
        }
    }

    @Override
    public void createJourney(String id, Passanger passanger, Station from) {
        MetroCard metroCard = metroCardService.getCard(id);
        Bill bill = fareCalculationService.getBill(passanger, metroCard);
        if (bill.getPayable() > metroCard.getBalance()) {
            RechargeSummary rechargeSummary = metroCardService.rechargeMetroCard(metroCard.getId(),
                    bill.getPayable() - metroCard.getBalance());
        } else {

        }

        List<Journey> list;
        if (journeysMap.containsKey(id)) {
            list = journeysMap.get(id);
        } else {
            list = new ArrayList<>();
        }
        list.add(new Journey(passanger, from, bill.getFare(), bill.getDiscount()));
        journeysMap.put(id, list);

    }

    public void updateRepo(Journey journey, int charges) {

        Map<PassangerType, PassengerSummary> passengerMap = passengerSummarys.get(journey.getFrom());
        PassengerSummary passengerSummary;
        if (passengerMap.containsKey(journey.getPassengerType())) {
            passengerSummary = passengerMap.get(journey.getPassengerType());
        } else {
            passengerSummary = new PassengerSummary(journey.getPassengerType(), 0);
        }

        passengerSummary.addCount();
        passengerMap.put(journey.getPassengerType(), passengerSummary);
        passengerSummarys.put(journey.getFrom(), passengerMap);

        CollectionSummary collectionSummary = stationSummary.get(journey.getFrom());
        collectionSummary.addToCollection(charges + journey.getFare() - journey.getDiscount());
        collectionSummary.addToDiscount(journey.getDiscount());
        stationSummary.put(journey.getFrom(), collectionSummary);

    }

    @Override
    public CollectionSummary getStationSummary(StationType station) {
        return stationSummary.get(station);
    }

    public Map<StationType, CollectionSummary> getCollectionSummaries() {
        return this.stationSummary;
    }

    public Map<PassangerType, PassengerSummary> getPassengerSummary(StationType station) {
        return passengerSummarys.get(station);
    }

    public List<Journey> getJourneysOf(String id) {
        return journeysMap.get(id);
    }

    // public Map<StationType, Double> getCharges() {
    // return this.collectedFees;
    // }

}
