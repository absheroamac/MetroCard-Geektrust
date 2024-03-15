package com.example.geektrust.entities;

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

    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null != )
    }

}
