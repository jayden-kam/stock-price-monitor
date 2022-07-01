package jayden.demo.stock_price_monitor;

import jayden.demo.stock_price_monitor.models.prices.PriceService;
import jayden.demo.stock_price_monitor.models.sources.SourceService;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockPriceMonitorApplicationTests {

    @Autowired
    private PriceService priceService;
    @Autowired
    private TickerService tickerService;
    @Autowired
    private SourceService sourceService;

    @Test
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
    }

}
