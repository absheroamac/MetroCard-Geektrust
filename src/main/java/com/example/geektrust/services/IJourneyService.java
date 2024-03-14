package com.example.geektrust.services;

import java.util.List;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;

public interface IJourneyService {

    public Journey createJourney(MetroCard metroCard, Passanger passanger, Station from);

    public List<Journey> getTripsFrom(StationType station);

    public List<Journey> getJourneysOf(String id);

    public Journey createJourney(MetroCard metroCard, Passanger passanger, Station from, double fare, double discount,
            double charges);

    // public List<Journey> getTripsOf(PassangerType passangerType);

}
