package test.java.com.example.geektrust.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
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

}
