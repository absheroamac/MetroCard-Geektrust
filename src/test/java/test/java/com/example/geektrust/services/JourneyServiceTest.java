package test.java.com.example.geektrust.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.entities.Journey;
import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.entities.Passanger;
import com.example.geektrust.entities.PassangerType;
import com.example.geektrust.entities.Station;
import com.example.geektrust.entities.StationType;
import com.example.geektrust.services.IFareCalculationService;
import com.example.geektrust.services.IMetroCardService;
import com.example.geektrust.services.JourneyService;
import com.example.geektrust.utils.Bill;

@ExtendWith(MockitoExtension.class)
public class JourneyServiceTest {

    @Mock
    IMetroCardService metroCardService;

    @Mock
    IFareCalculationService fareCalculationService;

    @InjectMocks
    JourneyService journeyService;

    @Test
    public void createJourneyMethodShouldCreateAJourneyMethodandReturn() {
        // Arrange
        MetroCard metroCard = new MetroCard("MC1", 200);
        Passanger passanger = new Passanger(PassangerType.ADULT);
        Station station = new Station(StationType.CENTRAL);

        Bill bill = new Bill(200, 0, 200);

        Journey expected = new Journey(passanger, station, 200, 0);

        Map<String, List<Journey>> journeys = new HashMap<>();
        // journeys.put("MC1", expected);

        journeyService.setJourneys(journeys);
        // journeys.put("MC1",Arrays.asList(expected));

        when(metroCardService.getCard("MC1")).thenReturn(metroCard);
        when(fareCalculationService.getBill(passanger, metroCard)).thenReturn(bill);

        // act
        journeyService.createJourney(metroCard.getId(), passanger, station);
        Map<String, List<Journey>> actual = journeyService.getJourneys();

        // assert
        assertEquals(expected, actual.get("MC1").get(0));

    }

}
