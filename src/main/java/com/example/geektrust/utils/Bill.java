package com.example.geektrust.utils;

public class Bill {

    private int fare;
    private int discount;
    private int payable;

    public Bill(int fare, int discount, int payable) {
        this.fare = fare;
        this.discount = discount;
        this.payable = payable;
    }

    public int getFare() {
        return fare;
    }

    public int getDiscount() {
        return discount;
    }

    public int getPayable() {
        return payable;
    }

}
