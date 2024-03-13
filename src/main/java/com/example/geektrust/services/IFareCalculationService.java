package com.example.geektrust.services;

import com.example.geektrust.entities.Passanger;
import com.example.geektrust.utils.Bill;

public interface IFareCalculationService {

    public double calculateFare(Passanger passanger);

}
