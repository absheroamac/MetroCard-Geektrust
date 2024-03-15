package com.example.geektrust.entities;

import java.util.Objects;

public class Station {

    StationType stationType;

    public Station(StationType stationType) {
        this.stationType = stationType;
    }

    public StationType getStationType() {
        return stationType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Station that = (Station) obj;

        return this.getStationType() == that.getStationType();
    }

    public int hashCode() {
        return Objects.hash(this.getStationType());
    }

}
