package jayden.demo.stock_price_monitor.services;

import jayden.demo.stock_price_monitor.models.prices.Price;
import jayden.demo.stock_price_monitor.models.prices.PriceService;
import jayden.demo.stock_price_monitor.models.prices.PriceServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {

    private PriceService priceService;

    @Before
    public void init() throws InterruptedException {
        priceService = new PriceServiceImpl();
        priceService.add(new Price(1, 0.01));
        priceService.add(new Price(2, 0.11));
        Thread.sleep(100);
        priceService.add(new Price(2, 0.12));
        Thread.sleep(100);
        priceService.add(new Price(2, 0.13));
        priceService.add(new Price(3, 1.11));
    }

    @Test
    public void addPrice_isSuccess() {
        priceService.add(new Price(4, 11.11));
        Assert.assertEquals(6, priceService.findAll().size());
    }

    @Test
    public void addPrices_isFailed() {
        Price invalidPrice = new Price(4, 11.11);
        invalidPrice.setId(4);
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> priceService.add(invalidPrice));
        Assert.assertTrue(exception.getMessage().contains("Cannot add price already exist"));
    }

    @Test
    public void findByTickerId_isSuccess() {
        Assert.assertEquals(1, priceService.findByTickerId(1).size());
        Assert.assertEquals(3, priceService.findByTickerId(2).size());
        Assert.assertEquals(1, priceService.findByTickerId(3).size());
        Assert.assertEquals(0, priceService.findByTickerId(4).size());
    }

    @Test
    public void findByTickerIdAndLatest_isSuccess() {
        Assert.assertEquals(1, priceService.findByTickerIdAndLatest(1, 5).size());
        Assert.assertEquals(3, priceService.findByTickerIdAndLatest(2, 5).size());
        Assert.assertEquals(1, priceService.findByTickerIdAndLatest(3, 5).size());
        Assert.assertEquals(0, priceService.findByTickerIdAndLatest(4, 5).size());
    }

    @Test
    public void findByTickerIdAndLatestOne_isSuccess() {
        Assert.assertEquals(0.01, priceService.findByTickerIdAndLatestOne(1).getAmount(), 0.00);
        Assert.assertEquals(0.13, priceService.findByTickerIdAndLatestOne(2).getAmount(), 0.00);
        Assert.assertEquals(1.11, priceService.findByTickerIdAndLatestOne(3).getAmount(), 0.00);
    }
}
