package com.example.geektrust.entities;

import java.util.Objects;

public class Journey {

    Passanger passanger;
    Station from;
    double fare;
    double discount;

    public Journey(Passanger passanger, Station from, Station to, double fare, double discount) {
        this.passanger = passanger;
        this.from = from;
        this.fare = fare;
        this.discount = discount;
    }

    public double getFare() {
        return fare;
    }

    public double getDiscount() {
        return discount;
    }

    public StationType getFrom() {
        return from.getStationType();

    }

    public PassangerType getPassengerType() {
        return passanger.getPassangerType();

    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Journey that = (Journey) obj;
        return this.getFare() == that.getFare()
                && this.getDiscount() == that.getDiscount() && this.getFrom() == that.getFrom()
                && this.getPassengerType() == that.getPassengerType();

    }

    public int hashCode() {
        return Objects.hash(this.getFare(), this.getDiscount(), this.getFrom(),
                this.getPassengerType());
    }

    public String toString() {

        return passanger.getPassangerType() + " " + from.getStationType() + " " + " " + fare + " "
                + discount;

    }

}
