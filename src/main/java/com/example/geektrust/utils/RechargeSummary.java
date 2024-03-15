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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        RechargeSummary that = (RechargeSummary) obj;
        return this.getMetroCard() == that.getMetroCard() && this.getCharges() == that.getCharges();
    }

}
