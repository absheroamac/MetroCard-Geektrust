package test.java.com.example.geektrust.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.services.FareCalculationService;
import com.example.geektrust.services.IJourneyService;

@ExtendWith(MockitoExtension.class)
public class FareCalculationSummaryTest {

    @Mock
    IJourneyService journeyService;

    @InjectMocks
    FareCalculationService fareCalculation;

    @Test
    public void calculateFareShouldCalculateTheFareForAdult() {
        // arrange
        Passanger passanger = new Passanger(PassangerType.ADULT);
        double expected = 200;

        // act
        double actual = fareCalculation.calculateFare(passanger);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    public void calculateFareShouldCalculateTheFareForKids() {
        // arrange
        Passanger passanger = new Passanger(PassangerType.KID);
        double expected = 50;

        // act
        double actual = fareCalculation.calculateFare(passanger);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    public void calculateFareShouldCalculateTheFareForSeniorCitizen() {
        // arrange
        Passanger passanger = new Passanger(PassangerType.SENIOR_CITIZEN);
        double expected = 100;

        // act
        double actual = fareCalculation.calculateFare(passanger);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    public void checkForDiscountShouldReturnTheDiscount() {

        Journey journey = new Journey(new Passanger(PassangerType.ADULT), new Station(StationType.AIRPORT), 200, 0);
        when(journeyService.getJourneysOf("MC1")).thenReturn(Arrays.asList(journey));

        int actual = fareCalculation.checkForDiscount(new MetroCard("MC1", 200), 200);

        assertEquals(actual, 100);

    }

}
