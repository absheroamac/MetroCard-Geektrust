package com.example.geektrust.services;

import com.example.geektrust.entities.Passanger;
import com.example.geektrust.utils.Bill;

public class FareCalculationService implements IFareCalculationService {

    IJourneyService journeyService;

    public FareCalculationService(IJourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @Override
    public double calculateFare(Passanger passanger) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateFare'");
    }

}
