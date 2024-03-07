package main.java.com.example.geektrust.services;

import java.util.List;

import main.java.com.example.geektrust.entities.Journey;
import main.java.com.example.geektrust.entities.Passanger;
import main.java.com.example.geektrust.entities.Station;

public interface IJourneyService {

    public Journey createJourney(Passanger passanger, Station from);

    public List<Journey> getTripsFrom(Station station);

}
