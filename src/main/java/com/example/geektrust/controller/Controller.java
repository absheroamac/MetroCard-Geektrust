package com.example.geektrust.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileInputStream;
import java.util.Scanner;

import com.example.geektrust.constants.Common;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.services.FareCalculationService;
import com.example.geektrust.services.IFareCalculationService;
import com.example.geektrust.services.IJourneyService;
import com.example.geektrust.services.IMetroCardService;
import com.example.geektrust.services.ISummaryService;
import com.example.geektrust.services.JourneyService;
import com.example.geektrust.services.MetroCardService;
import com.example.geektrust.services.SummaryService;
import com.example.geektrust.utils.Bill;
import com.example.geektrust.utils.RechargeSummary;

public class Controller implements IController {

    // Creating Service objects

    IMetroCardService metroCardService;
    IJourneyService journeyService;
    IFareCalculationService fareCalculationService;
    ISummaryService summaryService;

    public Controller(IMetroCardService metroCardService, IJourneyService journeyService,
            IFareCalculationService fareCalculationService, ISummaryService summaryService) {

        this.metroCardService = metroCardService;
        this.journeyService = journeyService;
        this.fareCalculationService = fareCalculationService;
        this.summaryService = summaryService;

        fareCalculationService.setJourneyService(journeyService);

        // creating passengertypes and stationtypes available.

    }

    public void createMetroCard(String id, String balance) {

        metroCardService.createMetroCard(id, balance);

    }

    public void createJourney(String id, String passengerType, String fromStation) {

        Passanger passanger = new Passanger(PassangerType.valueOf(passengerType));
        Station station = new Station(StationType.valueOf(fromStation));

        MetroCard metroCard = metroCardService.getCard(id);
        journeyService.createJourney(id, passanger, station);
    }

    public void printSummary() {

        summaryService.buildSummary();
        summaryService.printSummary();

    }

    public void start(String filename) throws IOException {
        List<List<String>> inputs = readInput(filename);
        // System.out.println(inputs.toString());
        for (List<String> input : inputs) {
            distributeInputs(input);
        }
    }

    public void distributeInputs(List<String> input) {

        // System.out.println(input.toString());

        switch (input.get(Common.ZERO)) {
            case "BALANCE":

                createMetroCard(input.get(Common.ONE), input.get(Common.TWO));

                break;
            case "CHECK_IN":

                createJourney(input.get(Common.ONE), input.get(Common.TWO), input.get(Common.THREE));

                break;

            case "PRINT_SUMMARY":

                printSummary();

            default:
                break;
        }

    }

    // public List<List<String>> readInput(String filename) throws IOException {
    // List<List<String>> inputs = new ArrayList<>();
    // try (BufferedReader reader = new BufferedReader(
    // new InputStreamReader(getClass().getResourceAsStream(filename)))) {

    // String line;

    // while ((line = reader.readLine()) != null) {

    // // System.out.println(line);

    // inputs.add(Arrays.asList(line.split(" ")));
    // }

    // } catch (IOException e) {
    // System.out.println("Error handling file \n" + e.getMessage());
    // // return null;

    // }

    // return inputs;

    // }

    public List<List<String>> readInput(String filename) throws IOException {
        List<List<String>> inputs = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filename);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {

                String line = sc.nextLine();
                inputs.add(Arrays.asList(line.split(" ")));
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
            System.out.println("Error handling file \n" + e.getMessage());
            // // return null;
        }

        return inputs;

    }

}
