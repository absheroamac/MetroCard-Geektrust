package com.example.geektrust.services;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.utils.Bill;

public interface IFareCalculationService {
    /**
     * Calculate the fare based on the Passanger type
     * 
     * @param passanger Passenger type
     * @return return calculated fare
     */
    public int calculateFare(Passanger passanger);

    /**
     * Will return the Bill, which call calculateFare method to get the fare and
     * call checkForDiscount method
     * 
     * @param passanger Passenger Type
     * @param metroCard MetroCard object
     * @return Return Bill object with fare,discount and payable(dare-discount)
     */

    public Bill getBill(Passanger passanger, MetroCard metroCard);

    /**
     * Method will set JourneyService
     * 
     * @param journeyService
     */

    public void setJourneyService(IJourneyService journeyService);

}
