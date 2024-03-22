package test.java.com.example.geektrust.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.entities.MetroCard;
import com.example.geektrust.services.IMetroCardService;
import com.example.geektrust.services.MetroCardService;
import com.example.geektrust.utils.RechargeSummary;

@ExtendWith(MockitoExtension.class)
public class MetroCardServiceTest {

    @InjectMocks
    MetroCardService metroCardService;

    @Test
    public void createMetroCardShouldCreateMetroObjectIfNotThereAndReturnTheObject() {
        // Arrange
        String id = "MC1";
        String balance = "200";
        double convertedBalance = Double.parseDouble(balance);
        MetroCard expected = new MetroCard(id, convertedBalance);
        metroCardService.setMetroCards(new HashMap<String, MetroCard>());

        // act
        MetroCard actual = metroCardService.createMetroCard(id, balance);

        // assert
        assertEquals(expected, actual);

    }

    @Test
    public void rechargeMetroCardWithRequiredAmountThenReturnWithServiceCharge() {
        // Arrange
        String id = "MC1";
        int amountRequired = 100;
        MetroCard metroCard = new MetroCard(id, 200);

        Map<String, MetroCard> cards = new HashMap<>();
        cards.put("MC1", metroCard);
        metroCardService.setMetroCards(cards);
        RechargeSummary expected = new RechargeSummary(metroCard, 2);

        // act
        RechargeSummary actual = metroCardService.rechargeMetroCard(id,
                amountRequired);

        // assert
        assertEquals(expected, actual);

    }

}
