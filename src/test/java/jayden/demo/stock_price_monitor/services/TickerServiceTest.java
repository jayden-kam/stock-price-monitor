package jayden.demo.stock_price_monitor.services;

import jayden.demo.stock_price_monitor.models.tickers.Ticker;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import jayden.demo.stock_price_monitor.models.tickers.TickerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TickerServiceTest {

    private TickerService tickerService;

    @Before
    public void init() {
        tickerService = new TickerServiceImpl();
        tickerService.add(new Ticker(1, "ABC"));
        tickerService.add(new Ticker(1, "DEF"));
        tickerService.add(new Ticker(2, "GHI"));
    }

    @Test
    public void addTicker_isSuccess() {
        tickerService.add(new Ticker(4, "JKL"));
        Assert.assertEquals(4, tickerService.findAll().size());
    }

    @Test
    public void addTicker_isFailed() {
        Ticker invalidTicker = new Ticker(4, "JKL");
        invalidTicker.setId(4);
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> tickerService.add(invalidTicker));
        Assert.assertTrue(exception.getMessage().contains("Cannot add ticker already exist"));
    }

    @Test
    public void findBySourceId_isSuccess() {
        Assert.assertEquals(2, tickerService.findBySourceId(1).size());
        Assert.assertEquals(1, tickerService.findBySourceId(2).size());

    }

    @Test
    public void findById_isSuccess() {
        Assert.assertEquals("ABC", tickerService.findById(1).getName());
        Assert.assertEquals("DEF", tickerService.findById(2).getName());
        Assert.assertEquals("GHI", tickerService.findById(3).getName());
    }
}
