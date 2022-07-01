package jayden.demo.stock_price_monitor.services;

import jayden.demo.stock_price_monitor.models.prices.Price;
import jayden.demo.stock_price_monitor.models.prices.PriceService;
import jayden.demo.stock_price_monitor.models.prices.PriceServiceImpl;
import jayden.demo.stock_price_monitor.models.sources.Source;
import jayden.demo.stock_price_monitor.models.sources.SourceService;
import jayden.demo.stock_price_monitor.models.sources.SourceServiceImpl;
import jayden.demo.stock_price_monitor.models.tickers.Ticker;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import jayden.demo.stock_price_monitor.models.tickers.TickerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SourceServiceTest {

    private SourceService sourceService;

    @Before
    public void init() {

        PriceService priceService = new PriceServiceImpl();
        priceService.add(new Price(1, 0.01));
        priceService.add(new Price(2, 0.11));
        priceService.add(new Price(2, 0.12));
        priceService.add(new Price(2, 0.13));
        priceService.add(new Price(3, 1.11));
        TickerService tickerService = new TickerServiceImpl(priceService);
        tickerService.add(new Ticker(1, "ABC"));
        tickerService.add(new Ticker(1, "DEF"));
        tickerService.add(new Ticker(2, "GHI"));
        sourceService = new SourceServiceImpl(tickerService);
        sourceService.add(new Source("X"));
        sourceService.add(new Source("Y"));
    }

    @Test
    public void addSource_isSuccess() {
        sourceService.add(new Source("Z"));
        Assert.assertEquals(3, sourceService.findAll().size());
    }

    @Test
    public void addSource_isFailed() {
        Source invalidSource = new Source("Z");
        invalidSource.setId(3);
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> sourceService.add(invalidSource));
        Assert.assertTrue(exception.getMessage().contains("Cannot add source already exist"));
    }

    @Test
    public void findById_isSuccess() {
        Assert.assertEquals(2, sourceService.findById(1).getTickers().size());
        Assert.assertEquals(1, sourceService.findById(2).getTickers().size());
    }
}
