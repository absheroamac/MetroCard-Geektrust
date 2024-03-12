package com.example.geektrust.utils;

import com.example.geektrust.entities.StationType;

public class CollectionSummary {

    StationType station;
    double totalCollection;
    double totalDiscount;

    public CollectionSummary(StationType station) {
        this.station = station;
        totalCollection = 0;
        totalDiscount = 0;
    }

    public void addToCollection(double amount) {

        this.totalCollection += amount;

    }

    public void addToDiscount(double discount) {
        this.totalDiscount += discount;
    }

    public StationType getStation() {
        return this.station;
    }

    public double getTotalCollection() {
        return this.totalCollection;
    }

    public double getTotalDiscount() {
        return this.totalDiscount;
    }

}
