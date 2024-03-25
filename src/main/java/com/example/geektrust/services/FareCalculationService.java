package com.example.geektrust.services;

import java.util.List;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.constants.Common;
import com.example.geektrust.entities.Journey;
import com.example.geektrust.utils.Bill;
import com.example.geektrust.entities.PassangerType;

public class FareCalculationService implements IFareCalculationService {

    IJourneyService journeyService;

    @Override
    public int calculateFare(Passanger passanger) {

        switch (passanger.getPassangerType().toString()) {
            case "ADULT":
                return Common.ADULT;
            case "SENIOR_CITIZEN":
                return Common.SENIOR_CITIZEN;
            case "KID":
                return Common.CHILD;

            default:
                return Common.ZERO;
        }
    }

    public Bill getBill(Passanger passanger, MetroCard metroCard) {

        int fare = calculateFare(passanger);
        int discount = checkForDiscount(metroCard, fare);
        int payable = fare - discount;

        return new Bill(fare, discount, payable);

    }

    public int checkForDiscount(MetroCard metroCard, int fare) {
        if (journeyService.getJourneysOf(metroCard.getId()) == null) {
            return Common.ZERO;
        }

        List<Journey> journeys = journeyService.getJourneysOf(metroCard.getId());
        int discount = calculateDiscount(journeys.size(), fare);
        return discount;
    }

    private int calculateDiscount(int numberOfJourneys, int fare) {
        if (numberOfJourneys == Common.ZERO || numberOfJourneys % Common.TWO == Common.ZERO) {
            return Common.ZERO;
        } else {
            return fare / Common.TWO;
        }
    }

    public void setJourneyService(IJourneyService journeyService) {
        this.journeyService = journeyService;
    }

}
