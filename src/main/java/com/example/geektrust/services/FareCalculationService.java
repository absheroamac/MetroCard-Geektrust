package com.example.geektrust.services;

import java.util.List;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.Journey;
import com.example.geektrust.utils.Bill;
import com.example.geektrust.entities.PassangerType;

public class FareCalculationService implements IFareCalculationService {

    IJourneyService journeyService;

    public FareCalculationService(IJourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @Override
    public double calculateFare(Passanger passanger) {

        if (passanger.getPassangerType() == PassangerType.ADULT) {
            return 200;
        }
        if (passanger.getPassangerType() == PassangerType.SENIOR_CITIZEN) {
            return 100;
        } else {
            return 50;
        }
    }

    public Bill getBill(Passanger passanger, MetroCard metroCard) {

        double fare = calculateFare(passanger);
        double discount = checkForDiscount(metroCard, fare);

        return new Bill(fare, discount);

    }

    public double checkForDiscount(MetroCard metroCard, double fare) {

        double discount;

        if (journeyService.getJourneysOf(metroCard.getId()) == null) {

            return 0;

        }

        List<Journey> journeys = journeyService.getJourneysOf(metroCard.getId());

        if (journeys.size() == 0 || journeys.size() % 2 == 0) {
            discount = 0;
        } else {
            discount = fare / 2;
        }

        return discount;

    }

}
