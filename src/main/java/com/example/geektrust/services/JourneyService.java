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
    public Journey createJourney(String id, Passanger passanger, Station from) {
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
