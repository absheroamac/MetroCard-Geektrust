package com.example.geektrust.services;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.utils.Bill;

public interface IFareCalculationService {

    public int calculateFare(Passanger passanger);

    public Bill getBill(Passanger passanger, MetroCard metroCard);

}
