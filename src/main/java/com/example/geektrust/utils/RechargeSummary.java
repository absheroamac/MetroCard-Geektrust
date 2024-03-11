package com.example.geektrust.utils;

import com.example.geektrust.entities.MetroCard;

public class RechargeSummary {

    private MetroCard metroCard;
    private double charges;

    public RechargeSummary(MetroCard metroCard, double charges) {
        this.charges = charges;
    }

    public double getCharges() {
        return this.charges;
    }

    public MetroCard getMetroCard() {
        return this.metroCard;
    }

}
