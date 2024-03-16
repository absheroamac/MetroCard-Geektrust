package com.example.geektrust.entities;

import java.util.Objects;

public class Journey {

    Passanger passanger;
    Station from;
    Station to;
    double fare;
    double discount;
    double charges;

    public Journey(Passanger passanger, Station from, Station to, double fare, double discount, double charges) {
        this.passanger = passanger;
        this.from = from;
        this.to = to;
        this.fare = fare;
        this.discount = discount;
        this.charges = charges;
    }

    public Journey(Passanger passanger, Station from, Station to, double fare, double discount) {
        this.passanger = passanger;
        this.from = from;
        this.to = to;
        this.fare = fare;
        this.discount = discount;
    }

    public double getFare() {
        return fare;
    }

    public double getDiscount() {
        return discount;
    }

    public double getCharges() {
        return charges;
    }

    public StationType getFrom() {
        return from.getStationType();

    }

    public PassangerType getPassengerType() {
        return passanger.getPassangerType();

    }

    public StationType getTo() {
        return to.getStationType();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Journey that = (Journey) obj;
        return this.getCharges() == that.getCharges() && this.getFare() == that.getFare()
                && this.getDiscount() == that.getDiscount() && this.getFrom() == that.getFrom()
                && this.getPassengerType() == that.getPassengerType() && this.getTo() == that.getTo();

    }

    public int hashCode() {
        return Objects.hash(this.getFare(), this.getCharges(), this.getDiscount(), this.getFrom(),
                this.getPassengerType(),
                this.getTo());
    }

    public String toString() {

        return passanger.getPassangerType() + " " + from.getStationType() + " " + to.getStationType() + " " + fare + " "
                + discount + " " + charges;

    }

}
