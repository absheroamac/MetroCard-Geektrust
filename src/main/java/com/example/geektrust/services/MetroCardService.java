package com.example.geektrust.services;

import java.util.Map;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.utils.RechargeSummary;

public class MetroCardService implements IMetroCardService {

    Map<String, MetroCard> cards;

    @Override
    public MetroCard createMetroCard(String id, String balance) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMetroCard'");
    }

    @Override
    public RechargeSummary rechargeMetroCard(String id, double amountRequired) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rechargeMetroCard'");
    }

    public void setMetroCards(Map<String, MetroCard> cards) {
        this.cards = cards;
    }

}
