package com.example.geektrust.services;

import java.util.HashMap;
import java.util.Map;

import com.example.geektrust.constants.Common;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.utils.RechargeSummary;

public class MetroCardService implements IMetroCardService {

    Map<String, MetroCard> cards;

    public MetroCardService() {
        cards = new HashMap<>();
    }

    @Override
    public MetroCard createMetroCard(String id, String balance) {

        if (cards.containsKey(id)) {
            return cards.get(id);
        } else {
            MetroCard card = new MetroCard(id, Double.parseDouble(balance));
            cards.put(card.getId(), card);
            return card;

        }

    }

    @Override
    public RechargeSummary rechargeMetroCard(String id, int amountRequired) {
        MetroCard card = cards.get(id);
        double charges = (int) amountRequired * Common.CHARGE;
        card.addBalance(amountRequired);
        RechargeSummary rechargeSummary = new RechargeSummary(card, (int) charges);

        cards.put(id, card);
        return rechargeSummary;
    }

    public void deductAmount(String id, int amount) {
        MetroCard card = cards.get(id);
        card.setBalance(card.getBalance() - amount);
        cards.put(id, card);
    }

    public void setMetroCards(Map<String, MetroCard> cards) {
        this.cards = cards;
    }

    public MetroCard getCard(String id) {
        return cards.get(id);
    }

}
