package jayden.demo.stock_price_monitor.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TickerControllerTest {

    @Autowired
    private TickerController tickerController;

    @Test
    public void testGetRequest() {
        Assertions.assertEquals(10, tickerController.get(null).size());
    }
}
