package jayden.demo.stock_price_monitor;

import jayden.demo.stock_price_monitor.models.prices.PriceService;
import jayden.demo.stock_price_monitor.models.sources.SourceService;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import jayden.demo.stock_price_monitor.services.PriceSourceService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockPriceMonitorApplicationTests {

    @Autowired
    private PriceService priceService;
    @Autowired
    private TickerService tickerService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private PriceSourceService priceSourceService;

    @Test
    @Order(1)
    void contextLoads() throws Exception {
        Assertions.assertEquals(3, sourceService.findAll().size());
        Assertions.assertEquals(4, tickerService.findBySourceId(1).size());
        Assertions.assertEquals(3, tickerService.findBySourceId(2).size());
        Assertions.assertEquals(3, tickerService.findBySourceId(3).size());
        Assertions.assertEquals(5, priceService.findByTickerId(1).size());
        Assertions.assertEquals(5, priceService.findByTickerId(2).size());
        Assertions.assertEquals(5, priceService.findByTickerId(3).size());
        Assertions.assertEquals(5, priceService.findByTickerId(4).size());
        Assertions.assertEquals(5, priceService.findByTickerId(5).size());
        Assertions.assertEquals(5, priceService.findByTickerId(6).size());
        Assertions.assertEquals(5, priceService.findByTickerId(7).size());
        Assertions.assertEquals(5, priceService.findByTickerId(8).size());
        Assertions.assertEquals(5, priceService.findByTickerId(9).size());
        Assertions.assertEquals(5, priceService.findByTickerId(10).size());
        Assertions.assertEquals("SRC 1", sourceService.findById(1).getName());
        Assertions.assertEquals("GOOGL", tickerService.findById(4).getName());
        Assertions.assertEquals(161.25, priceService.findByTickerIdAndLatestOne(9).getAmount());
    }

    @Test
    @Order(2)
    void testPriceSourceService() {
        sourceService.findAll().forEach(source -> priceSourceService.sourcing(source));
        Assertions.assertEquals(3, sourceService.findAll().size());
        Assertions.assertEquals(4, tickerService.findBySourceId(1).size());
        Assertions.assertEquals(3, tickerService.findBySourceId(2).size());
        Assertions.assertEquals(3, tickerService.findBySourceId(3).size());
        Assertions.assertEquals(6, priceService.findByTickerId(1).size());
        Assertions.assertEquals(6, priceService.findByTickerId(2).size());
        Assertions.assertEquals(6, priceService.findByTickerId(3).size());
        Assertions.assertEquals(6, priceService.findByTickerId(4).size());
        Assertions.assertEquals(6, priceService.findByTickerId(5).size());
        Assertions.assertEquals(6, priceService.findByTickerId(6).size());
        Assertions.assertEquals(6, priceService.findByTickerId(7).size());
        Assertions.assertEquals(6, priceService.findByTickerId(8).size());
        Assertions.assertEquals(6, priceService.findByTickerId(9).size());
        Assertions.assertEquals(6, priceService.findByTickerId(10).size());
        Assertions.assertEquals("SRC 1", sourceService.findById(1).getName());
        Assertions.assertEquals("GOOGL", tickerService.findById(4).getName());
        Assertions.assertNotEquals(161.25, priceService.findByTickerIdAndLatestOne(9).getAmount(), 0.0);
    }
}
