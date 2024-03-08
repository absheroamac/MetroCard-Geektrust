package main.java.com.example.geektrust.services;

import main.java.com.example.geektrust.utils.Bill;

public class FareCalculationService implements IFareCalculationService {

    IJourneyService journeyService;

    public FareCalculationService(IJourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @Override
    public Bill calculateFare() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateFare'");
    }

}
