package com.example.geektrust.utils;

import java.util.Objects;

import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.StationType;
import java.util.HashMap;
import java.util.Map;

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

    public void addCount() {
        count += 1;
    }

    @Override
    public int compareTo(PassengerSummary o) {
        // TODO Auto-generated method stub
        if (this.getCount() == o.getCount()) {
            Map<String, Integer> hierarchy = new HashMap<>();
            hierarchy.put("ADULT", 3);
            hierarchy.put("KID", 2);
            hierarchy.put("SENIOR_CITIZEN", 1);

            int thisHierarchy = hierarchy.get(this.getPassenger());
            int thatHierarchy = hierarchy.get(o.getPassenger());

            return thatHierarchy - thisHierarchy;

        }
        return this.getCount() - o.getCount();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PassengerSummary that = (PassengerSummary) obj;
        return passenger == that.passenger && count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(passenger, count);
    }
}
