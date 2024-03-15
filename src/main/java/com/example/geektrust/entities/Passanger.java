package com.example.geektrust.entities;

import java.util.Objects;

public class Passanger {

    PassangerType passangerType;

    public Passanger(PassangerType passangerType) {
        this.passangerType = passangerType;
    }

    public PassangerType getPassangerType() {
        return this.passangerType;
    }

    @Override

    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Passanger that = (Passanger) obj;
        return this.getPassangerType() == that.getPassangerType();

    }

    public int hashCode() {
        return Objects.hash(this.getPassangerType());
    }

}
