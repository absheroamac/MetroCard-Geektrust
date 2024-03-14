package test.java.com.example.geektrust.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.services.JourneyService;

@ExtendWith(MockitoExtension.class)
public class JourneyServiceTest {

    JourneyService journeyService;

    @Test
    public void createJourneyMethodShouldCreateAJourneyMethodandReturn() {
        // Arrange
        MetroCard metroCard = new MetroCard("MC1", 200);
        Passanger passanger = new Passanger(PassangerType.ADULT);
        Station station = new Station(StationType.CENTRAL);

        Journey expected = new Journey(passanger, station, station, 200, 0);

        Map<String, Journey> journeys = new HashMap<>();
        // journeys.put("MC1",expected);

        journeyService.setJourneys(journeys);

        // act
        Journey actual = journeyService.createJourney(metroCard, passanger, station);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    public void getTripsFromShouldReturnListOfTripsFromInputedStation() {
        // Arrange
        MetroCard metroCard = new MetroCard("MC1", 200);
        Passanger passanger = new Passanger(PassangerType.KID);
        Station station = new Station(StationType.AIRPORT);

        Journey journey = new Journey(passanger, station, station, 50, 0);

        List<Journey> journeys = Arrays.asList(journey);
        Map<String, List<Journey>> journeysMap = new HashMap<>();

        journeysMap.put("AIRPORT", journeys);
        journeyService.setJourneysMap(journeysMap);

        //
        List<Journey> actual = journeyService.getTripsFrom(StationType.AIRPORT);

        assertEquals(journeys, actual);

    }

}
