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
    public void init() {
        priceService = new PriceServiceImpl();
        priceService.add(new Price(1, 0.01));
        priceService.add(new Price(2, 0.11));
        priceService.add(new Price(2, 0.12));
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
}
