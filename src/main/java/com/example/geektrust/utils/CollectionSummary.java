package com.example.geektrust.utils;

import com.example.geektrust.entities.StationType;
import java.util.Objects;

public class CollectionSummary {

    StationType station;
    int totalCollection;
    int totalDiscount;

    public CollectionSummary(StationType station) {
        this.station = station;
        totalCollection = 0;
        totalDiscount = 0;
    }

    public CollectionSummary(StationType station, int totalCollection, int totalDiscount) {
        this.station = station;
        this.totalCollection = totalCollection;
        this.totalDiscount = totalDiscount;
    }

    public void addToCollection(int amount) {

        this.totalCollection += amount;

    }

    public void addToDiscount(int discount) {
        this.totalDiscount += discount;
    }

    public StationType getStation() {
        return this.station;
    }

    public int getTotalCollection() {
        return this.totalCollection;
    }

    public int getTotalDiscount() {
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
