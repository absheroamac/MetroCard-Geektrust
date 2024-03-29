package test.java.com.example.geektrust.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.services.IJourneyService;
import com.example.geektrust.services.JourneyService;
import com.example.geektrust.services.SummaryService;
import com.example.geektrust.utils.CollectionSummary;
import com.example.geektrust.utils.PassengerSummary;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
public class SummaryServiceTest {

    @Mock
    IJourneyService journeyService;

    @InjectMocks
    SummaryService summaryService;

    @Test
    public void buildSummaryShouldReturnTheSummaryFromJourneyService() {

        // // TOTAL_COLLECTION CENTRAL 457 50
        // // PASSENGER_TYPE_SUMMARY
        // // DULT 2
        // // SENIOR_CITIZEN 1
        // // TOTAL_COLLECTION AIRPORT 252 100
        // // PASSENGER_TYPE_SUMMARY
        // // ADULT 1
        // // KID 1
        // // SENIOR_CITIZEN 1

        Map<StationType, CollectionSummary> collectionSummarys = new HashMap<>();
        CollectionSummary collectionSummary1 = new CollectionSummary(StationType.AIRPORT);
        CollectionSummary collectionSummary2 = new CollectionSummary(StationType.CENTRAL);
        collectionSummarys.put(StationType.AIRPORT, collectionSummary1);
        collectionSummarys.put(StationType.CENTRAL, collectionSummary2);

        Map<PassangerType, PassengerSummary> passengerSummarys1 = new HashMap<>();
        // Map<PassangerType,PassengerSummary> passengerSummarys2 = new HashMap<>();

        PassengerSummary passengerSummary1 = new PassengerSummary(PassangerType.ADULT, 3);
        PassengerSummary passengerSummary2 = new PassengerSummary(PassangerType.KID, 2);

        passengerSummarys1.put(PassangerType.ADULT, passengerSummary1);
        passengerSummarys1.put(PassangerType.KID, passengerSummary2);

        List<PassengerSummary> list = Arrays.asList(passengerSummary1, passengerSummary2);
        Collections.sort(list);

        when(journeyService.getCollectionSummaries()).thenReturn(collectionSummarys);
        when(journeyService.getPassengerSummary(any(StationType.class))).thenReturn(passengerSummarys1);

        summaryService.buildSummary();

        Map<StationType, List<PassengerSummary>> actualPassengerSummary = summaryService.getPassengerSummary();
        Map<StationType, CollectionSummary> actualCollectionSummary = summaryService.getCollectionSummary();

        assertEquals(actualPassengerSummary.get(StationType.AIRPORT), list);
        assertEquals(actualCollectionSummary.get(StationType.AIRPORT), collectionSummary1);

    }

    // }

    @Test
    public void printSummaryMethodShouldPrintTheSummary() {
        // Arrange
        final ByteArrayOutputStream outputStreamCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCapture));

        String expected = "TOTAL_COLLECTION CENTRAL 350 0\nPASSENGER_TYPE_SUMMARY\nADULT 3\nKID 2\nTOTAL_COLLECTION AIRPORT 50 0\nPASSENGER_TYPE_SUMMARY\nADULT 3\nKID 2";

        Map<StationType, CollectionSummary> collectionSummarys = new HashMap<>();
        CollectionSummary collectionSummary1 = new CollectionSummary(StationType.AIRPORT, 50, 0);
        CollectionSummary collectionSummary2 = new CollectionSummary(StationType.CENTRAL, 350, 0);
        collectionSummarys.put(StationType.AIRPORT, collectionSummary1);
        collectionSummarys.put(StationType.CENTRAL, collectionSummary2);

        Map<PassangerType, PassengerSummary> passengerSummarys1 = new HashMap<>();
        Map<StationType, List<PassengerSummary>> passengerSummarys = new HashMap<>();

        PassengerSummary passengerSummary1 = new PassengerSummary(PassangerType.ADULT, 3);
        PassengerSummary passengerSummary2 = new PassengerSummary(PassangerType.KID, 2);

        passengerSummarys1.put(PassangerType.ADULT, passengerSummary1);
        passengerSummarys1.put(PassangerType.KID, passengerSummary2);

        List<PassengerSummary> list = Arrays.asList(passengerSummary1, passengerSummary2);
        // Collections.sort(list);
        // System.out.println(list.toString());

        passengerSummarys.put(StationType.AIRPORT, list);
        passengerSummarys.put(StationType.CENTRAL, list);

        summaryService.setCollectionSummarys(collectionSummarys);
        summaryService.setPassengerSummary(passengerSummarys);

        summaryService.printSummary();

        assertEquals(expected.trim(), outputStreamCapture.toString().trim());

    }
}
// // //Act
// // geekdemy.printBill();

// // //Assert
// // verify(cart).calculateSubTotal();
// // verify(coupon).processCoupon();
// // assertEquals(expected.trim(), outputStreamCapture.toString().trim());

// // Journey journey1 = new Journey(new Passanger(PassangerType.ADULT), new
// // Station(StationType.CENTRAL),
// // new Station(StationType.AIRPORT), 200, 0);
// // Journey journey2 = new Journey(new Passanger(PassangerType.KIDS), new
// // Station(StationType.CENTRAL),
// // new Station(StationType.AIRPORT), 50, 0);
// // Journey journey3 = new Journey(new
// Passanger(PassangerType.SENIOR_CITIZEN),
// // new Station(StationType.CENTRAL),
// // new Station(StationType.AIRPORT), 100, 0);
// // Journey journey4 = new Journey(new Passanger(PassangerType.KIDS), new
// // Station(StationType.AIRPORT),
// // new Station(StationType.CENTRAL), 50, 0);

// // List<Journey> fromCentral = Arrays.asList(journey1, journey2, journey3);
// // List<Journey> fromAirport = Arrays.asList(journey4);

// List<PassangerType> passangerTypes = Arrays.asList(PassangerType.ADULT,
// PassangerType.KID,
// PassangerType.SENIOR_CITIZEN);
// List<StationType> stationTypes = Arrays.asList(StationType.CENTRAL,
// StationType.AIRPORT);

// summaryService.setTypes(passangerTypes, stationTypes);

// //
// when(journeyService.getTripsFrom(StationType.CENTRAL)).thenReturn(fromCentral);
// //
// when(journeyService.getTripsFrom(StationType.AIRPORT)).thenReturn(fromAirport);

// CollectionSummary collectionSummary1 = new
// CollectionSummary(StationType.CENTRAL, 350, 0);
// CollectionSummary collectionSummary2 = new
// CollectionSummary(StationType.AIRPORT, 50, 0);
// List<CollectionSummary> expectedCollectionSummary =
// Arrays.asList(collectionSummary1,
// collectionSummary2);

// Map<StationType, Map<PassangerType, PassengerSummary>>
// expectedPassengerSummary = new HashMap<>();

// Map<PassangerType, PassengerSummary> passengerSummary1 = new HashMap<>();
// passengerSummary1.put(PassangerType.ADULT, new
// PassengerSummary(PassangerType.ADULT, 1));
// passengerSummary1.put(PassangerType.KID, new
// PassengerSummary(PassangerType.KID, 1));
// passengerSummary1.put(PassangerType.SENIOR_CITIZEN,
// new PassengerSummary(PassangerType.SENIOR_CITIZEN, 1));

// Map<PassangerType, PassengerSummary> passengerSummary2 = new HashMap<>();
// passengerSummary2.put(PassangerType.KID, new
// PassengerSummary(PassangerType.KID, 1));

// expectedPassengerSummary.put(StationType.CENTRAL, passengerSummary1);
// expectedPassengerSummary.put(StationType.AIRPORT, passengerSummary2);

// summaryService.setCollectionSummarys(expectedCollectionSummary);
// summaryService.setPassengerSummary(expectedPassengerSummary);

// final ByteArrayOutputStream outputStreamCapture = new
// ByteArrayOutputStream();
// System.setOut(new PrintStream(outputStreamCapture));

// // Act
// summaryService.printSummary();

// // Assert
// assertEquals(expected.trim(), outputStreamCapture.toString().trim());

// }

// }
