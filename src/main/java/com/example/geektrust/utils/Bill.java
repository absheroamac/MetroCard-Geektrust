package com.example.geektrust.utils;

public class Bill {

    private double fare;
    private double discount;

    public Bill(double fare, double discount) {
        this.fare = fare;
        this.discount = discount;
    }

    public double getFare() {
        return fare;
    }

    public double getDiscount() {
        return discount;
    }

}
