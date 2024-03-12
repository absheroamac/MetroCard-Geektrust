package test.java.com.example.geektrust.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.entities.Station;
import com.example.geektrust.services.IJourneyService;
import com.example.geektrust.services.SummaryService;

@ExtendWith(MockitoExtension.class)
public class SummaryServiceTest {

    @Mock
    IJourneyService journeyService;

    @InjectMocks
    SummaryService summaryService;

    @Test
    public void buildSummaryShouldReturnTheSummaryFromJourneyService(){

        // TOTAL_COLLECTION CENTRAL 457 50
        // PASSENGER_TYPE_SUMMARY
        // DULT 2
        // SENIOR_CITIZEN 1
        // TOTAL_COLLECTION AIRPORT  252 100
        // PASSENGER_TYPE_SUMMARY
        // ADULT 1
        // KID 1
        // SENIOR_CITIZEN 1

        when(journeyService.getTripsFrom(any(Station.class))

        

    }

}
