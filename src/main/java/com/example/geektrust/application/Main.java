package com.example.geektrust.application;

import java.io.IOException;

import com.example.geektrust.constants.Common;
import com.example.geektrust.controller.Controller;
import com.example.geektrust.services.FareCalculationService;
import com.example.geektrust.services.IFareCalculationService;
import com.example.geektrust.services.IJourneyService;
import com.example.geektrust.services.IMetroCardService;
import com.example.geektrust.services.ISummaryService;
import com.example.geektrust.services.JourneyService;
import com.example.geektrust.services.MetroCardService;
import com.example.geektrust.services.SummaryService;

public class Main {
    public static void main(String[] args) throws IOException {

        IMetroCardService metroCardService = new MetroCardService();
        IFareCalculationService fareCalculationService = new FareCalculationService();
        IJourneyService journeyService = new JourneyService(metroCardService, fareCalculationService);
        ISummaryService summaryService = new SummaryService(journeyService);

        Controller controller = new Controller(metroCardService, journeyService, fareCalculationService,
                summaryService);

        String input = args[Common.ZERO];
        // String localTest = "input.txt";
        controller.start(input);
    }
}
