package com.example.geektrust.controller;

import java.io.IOException;
import java.util.List;

public interface IController {

    public void createMetroCard(String id, String balance);

    public void createJourney(String id, String passengerType, String fromStation);

    public void printSummary();

    public List<List<String>> readInput(String filename) throws IOException;

}
