package jayden.demo.stock_price_monitor.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PriceControllerTest {

    @Autowired
    private PriceController priceController;

    @Test
    public void testGetRequest() {
        Assertions.assertEquals(5, priceController.get(1, false).size());
        Assertions.assertEquals(5, priceController.get(5, true).size());
        Assertions.assertEquals(0, priceController.get(11, true).size());
    }
}
