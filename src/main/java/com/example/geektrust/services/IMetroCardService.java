package com.example.geektrust.services;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.utils.RechargeSummary;

public interface IMetroCardService {

    public MetroCard createMetroCard(String id, String balance);

    public RechargeSummary rechargeMetroCard(String metroCard, double amountRequired);

    public MetroCard getCard(String id);

    public void deductAmount(String id, double amount);

}
