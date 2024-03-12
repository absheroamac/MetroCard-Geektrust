package com.example.geektrust.utils;

import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.StationType;

public class PassengerSummary implements Comparable<PassengerSummary> {

    PassangerType passenger;
    int count;

    public PassengerSummary(PassangerType passenger, int count) {
        this.passenger = passenger;
        this.count = count;
    }

    public String getPassenger() {
        return this.passenger.toString();
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(PassengerSummary o) {
        // TODO Auto-generated method stub
        return this.getCount() - o.getCount();
    }

}
