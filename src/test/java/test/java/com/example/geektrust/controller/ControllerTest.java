package test.java.com.example.geektrust.controller;

import java.util.List;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.controller.Controller;
import com.example.geektrust.controller.IController;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.services.IFareCalculationService;
import com.example.geektrust.services.IJourneyService;
import com.example.geektrust.services.IMetroCardService;
import com.example.geektrust.services.ISummaryService;
import com.example.geektrust.utils.Bill;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    private static final String Passanger = null;

    @Mock
    IMetroCardService metrocardService;

    @Mock
    IJourneyService journeyService;

    @Mock
    IFareCalculationService fareCalculationService;

    @Mock
    ISummaryService summaryService;

    @Mock
    IController controllerMock;

    @InjectMocks
    Controller controller;

    @Test
    public void createMetroCardMethodShouldCallCreateMetroCardMethodFromMetroCardServiceWithRightAttributes() {

        // arrange
        String id = "MC1";
        String balance = "200";

        double convertedBalance = Double.parseDouble(balance);

        // act
        controller.createMetroCard(id, balance);

        // assert
        verify(metrocardService).createMetroCard(id, balance);

    }

    @Test
    public void createJourneyMethodShouldCallcreateJourneyMethodFromJourneyServiceWithRightAttributes() {

        // arrange
        String id = "MC1";
        String passengerType = "ADULT";
        String fromStation = "CENTRAL";

        MetroCard metroCard = new MetroCard(id, 200);
        Bill bill = new Bill(200, 0);

        Passanger passenger = new Passanger(PassangerType.ADULT);
        Station station = new Station(StationType.CENTRAL);

        when(metrocardService.getCard(id)).thenReturn(metroCard);

        when(fareCalculationService.getBill(any(Passanger.class), any(MetroCard.class))).thenReturn(bill);

        // act
        controller.createJourney(id, passengerType, fromStation);

        // assert
        verify(journeyService).createJourney(metroCard, passenger, station, 200, 0, 0);

    }

    @Test
    public void printSummaryMethodShouldPrintTheOutputInTheConsoleBasedOnTheJourneyService() {
        // Journey journey1 = new Journey(new Passanger(PassangerType.ADULT), new
        // Station(StationType.CENTRAL),new Station(StationType.CENTRAL), 200, 0);
        // Journey journey2 = new Journey(new Passanger(PassangerType.KIDS), new
        // Station(StationType.CENTRAL),new Station(StationType.AIRPORT), 50, 0);
        // Journey journey3 = new Journey(new Passanger(PassangerType.ADULT), new
        // Station(StationType.AIRPORT),new Station(StationType.CENTRAL), 200, 100);
        // Journey journey4 = new Journey(new Passanger(PassangerType.SENIOR_CITIZEN),
        // new Station(StationType.AIRPORT),new Station(StationType.CENTRAL), 100, 0);
        // List<Journey> mc1 = Arrays.asList(journey1,journey3);
        // List<Journey> mc2 = Arrays.asList(journey2);
        // List<Journey> mc3 = Arrays.asList(journey4);

        // act
        controller.printSummary();

        // assert
        verify(summaryService).buildSummary();
        verify(summaryService).printSummary();

    }

    @Test
    public void readInputMethodShouldReadTheFileAndGiveListOfCommends() throws IOException {
        String filename = "/input.txt";
        List<String> input1 = Arrays.asList("BALANCE", "MC1", "400");
        List<String> input2 = Arrays.asList("BALANCE", "MC2", "100");
        List<String> input3 = Arrays.asList("BALANCE", "MC3", "50");
        List<String> input4 = Arrays.asList("BALANCE", "MC4", "50");
        List<String> input5 = Arrays.asList("CHECK_IN", "MC1", "SENIOR_CITIZEN", "AIRPORT");
        List<String> input6 = Arrays.asList("CHECK_IN", "MC2", "KID", "AIRPORT");
        List<String> input7 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "CENTRAL");
        List<String> input8 = Arrays.asList("CHECK_IN", "MC1", "SENIOR_CITIZEN", "CENTRAL");
        List<String> input9 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "AIRPORT");
        List<String> input10 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "CENTRAL");
        List<String> input11 = Arrays.asList("PRINT_SUMMARY");

        List<List<String>> expected = Arrays.asList(input1, input2, input3, input4, input5, input6, input7, input8,
                input9, input10, input11);

        // Act
        List<List<String>> actual = controller.readInput(filename);

        // Assert
        assertEquals(expected, actual);

    }

    @Test
    public void readInputMethodShouldReturnIOExceptionForWrongFileName() {
        String filename = "/inputs.txt";
        List<String> input1 = Arrays.asList("BALANCE", "MC1", "400");
        List<String> input2 = Arrays.asList("BALANCE", "MC2", "100");
        List<String> input3 = Arrays.asList("BALANCE", "MC3", "50");
        List<String> input4 = Arrays.asList("BALANCE", "MC4", "50");
        List<String> input5 = Arrays.asList("CHECK_IN", "MC1", "SENIOR_CITIZEN", "AIRPORT");
        List<String> input6 = Arrays.asList("CHECK_IN", "MC2", "KID", "AIRPORT");
        List<String> input7 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "CENTRAL");
        List<String> input8 = Arrays.asList("CHECK_IN", "MC1", "SENIOR_CITIZEN", "CENTRAL");
        List<String> input9 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "AIRPORT");
        List<String> input10 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "CENTRAL");
        List<String> input11 = Arrays.asList("PRINT_SUMMARY");

        List<List<String>> expected = Arrays.asList(input1, input2, input3, input4, input5, input6, input7, input8,
                input9, input10, input11);

        // Act

        // Assert
        assertThrows(NullPointerException.class, () -> {

            controller.readInput(filename);

        });

    }

    @Test
    public void distributeInputsMethodShouldInvokeCreateMetroCardMethodForBALANCEInput() {
        // Arrange
        List<String> input = Arrays.asList("BALANCE", "MC1", "200");

        // Act
        controller.distributeInputs(input);

        // Assert
        verify(controller).createMetroCard("MC1", "200");

    }

    // @Test
    // public void
    // distributeInputsMethodShouldInvokeCreateJourneyMethodForCHECK_INInput() {
    // List<String> input = Arrays.asList("CHECK_IN", "MC1", "ADULT", "CENTRAL");

    // // Act
    // controller.distributeInputs(input);

    // // Assert
    // verify(controller, times(1)).createJourney("MC1", "ADULT", "CENTRAL");

    // }
}

// @Test
// public void
// distributeInputsMethodShouldInvokePrintSummaryMethodForPRINTSUMMARYInput() {
// List<String> input = Arrays.asList("PRINT_SUMMARY");

// // act
// controller.distributeInputs(input);

// // assert
// verify(controllerMock).printSummary();

// }

// @Test
// public void startMethodShouldInvokeReadInputAndInvokeDistributeInputsNTimes()
// throws IOException {

// List<String> input1 = Arrays.asList("BALANCE", "MC1", "400");
// List<String> input2 = Arrays.asList("BALANCE", "MC2", "100");
// List<String> input3 = Arrays.asList("BALANCE", "MC3", "50");
// List<String> input4 = Arrays.asList("BALANCE", "MC4", "50");
// List<String> input5 = Arrays.asList("CHECK_IN", "MC1", "SENIOR_CITIZEN",
// "AIRPORT");
// List<String> input6 = Arrays.asList("CHECK_IN", "MC2", "KID", "AIRPORT");
// List<String> input7 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "CENTRAL");
// List<String> input8 = Arrays.asList("CHECK_IN", "MC1", "SENIOR_CITIZEN",
// "CENTRAL");
// List<String> input9 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "AIRPORT");
// List<String> input10 = Arrays.asList("CHECK_IN", "MC3", "ADULT", "CENTRAL");
// List<String> input11 = Arrays.asList("PRINT_SUMMARY");

// List<List<String>> input = Arrays.asList(input1, input2, input3, input4,
// input5, input6, input7, input8,
// input9, input10, input11);

// Controller controllerSpy = spy(new Controller());

// when(controllerSpy.readInput(anyString())).thenReturn(input);

// // act
// controllerSpy.start("filename.txt");

// // assert

// verify(controllerSpy).readInput(anyString());
// verify(controllerSpy, times(11)).distributeInputs(anyList());

// }

// }
