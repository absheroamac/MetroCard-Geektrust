package com.example.geektrust.services;

import java.util.List;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.Station;

public interface IJourneyService {

    public Journey createJourney(Passanger passanger, Station from);

    public List<Journey> getTripsFrom(Station station);

}
