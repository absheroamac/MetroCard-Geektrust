package com.example.geektrust.utils;

import com.example.geektrust.entities.StationType;
import java.util.Objects;

public class CollectionSummary {

    StationType station;
    double totalCollection;
    double totalDiscount;

    public CollectionSummary(StationType station) {
        this.station = station;
        totalCollection = 0;
        totalDiscount = 0;
    }

    public CollectionSummary(StationType station, double totalCollection, double totalDiscount) {
        this.station = station;
        this.totalCollection = totalCollection;
        this.totalDiscount = totalDiscount;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        CollectionSummary that = (CollectionSummary) obj;
        return that.getStation() == this.getStation() && that.getTotalCollection() == this.getTotalCollection()
                && that.getTotalDiscount() == that.getTotalDiscount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(station, totalCollection, totalDiscount);
    }

    @Override
    public String toString() {

        return station.toString() + " " + totalCollection + " " + totalDiscount;

    }

}
