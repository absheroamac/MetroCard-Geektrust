package main.java.com.example.geektrust.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.java.com.example.geektrust.services.FareCalculationService;
import main.java.com.example.geektrust.services.IFareCalculationService;
import main.java.com.example.geektrust.services.IJourneyService;
import main.java.com.example.geektrust.services.IMetroCardService;
import main.java.com.example.geektrust.services.JourneyService;
import main.java.com.example.geektrust.services.MetroCardService;

public class Controller {

    IMetroCardService metroCardService = new MetroCardService();
    IJourneyService journeyService = new JourneyService();
    IFareCalculationService fareCalculationService = new FareCalculationService(journeyService);

    public void createMetroCard(String id, String balance) {

    }

    public void createJourney(String id, String passengerType, String fromStation) {

    }

    public void printSummary() {

    }

    public void start(String filename) {
        List<List<String>> inputs = readInput(filename);
        for (List<String> input : inputs) {
            switch (input.get(0)) {
                case "BALANCE":
                    if (input.size() == 3) {

                    }

                    break;

                default:
                    break;
            }
        }
    }

    public List<List<String>> readInput(String filename) {
        List<List<String>> inputs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = reader.readLine()) != null) {

                inputs.add(Arrays.asList(line.split(" ")));
            }

        } catch (IOException e) {
            System.out.println("Error handling file \n" + e.getMessage());
            return null;

        }

        return inputs;

    }

}
