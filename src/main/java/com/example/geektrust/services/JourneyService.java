package com.example.geektrust.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.example.geektrust.constants.Common;
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
        int charges = bill.getPayable() > metroCard.getBalance() ? rechargeCardAndGetCharges(metroCard, bill)
                : Common.ZERO;

        Journey journey = new Journey(passanger, from, bill.getFare(), bill.getDiscount());

        updateRepo(journey, charges, from);

        metroCardService.deductAmount(id, bill.getPayable());

        addJourneyToList(id, journey);
    }

    private int rechargeCardAndGetCharges(MetroCard metroCard, Bill bill) {
        RechargeSummary rechargeSummary = metroCardService.rechargeMetroCard(metroCard.getId(),
                (int) (bill.getPayable() - metroCard.getBalance()));
        return rechargeSummary.getCharges();
    }

    private void updateRepo(Journey journey, int charges, Station from) {
        updatePassengerSummary(journey, from.getStationType());
        updateCollectionSummary(journey, charges, from.getStationType());
    }

    private void updatePassengerSummary(Journey journey, StationType from) {
        PassengerSummary passengerSummary = passengerSummarys.get(from).getOrDefault(journey.getPassengerType(),
                new PassengerSummary(journey.getPassengerType(), 0));
        passengerSummary.addCount();
        passengerSummarys.get(from).put(journey.getPassengerType(), passengerSummary);
    }

    private void updateCollectionSummary(Journey journey, int charges, StationType from) {
        CollectionSummary collectionSummary = stationSummary.get(from);
        collectionSummary.addToCollection((int) (charges + journey.getFare() - journey.getDiscount()));
        collectionSummary.addToDiscount((int) journey.getDiscount());
        stationSummary.put(from, collectionSummary);
    }

    private void addJourneyToList(String id, Journey journey) {
        journeysMap.computeIfAbsent(id, k -> new ArrayList<>()).add(journey);
    }

    @Override
    public CollectionSummary getStationSummary(StationType station) {
        return stationSummary.get(station);
    }

    @Override
    public Map<StationType, CollectionSummary> getCollectionSummaries() {
        return stationSummary;
    }

    @Override
    public Map<PassangerType, PassengerSummary> getPassengerSummary(StationType station) {
        return passengerSummarys.get(station);
    }

    @Override
    public List<Journey> getJourneysOf(String id) {
        return journeysMap.get(id);
    }
}