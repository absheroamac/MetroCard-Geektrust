package com.example.geektrust.entities;

public class Journey {

    Passanger passanger;
    Station from;
    Station to;
    double fare;
    double discount;

    public Journey(Passanger passanger, Station from, Station to, double fare, double discount) {
        this.passanger = passanger;
        this.from = from;
        this.to = to;
        this.fare = fare;
        this.discount = discount;
    }

}
