package jayden.demo.stock_price_monitor.services;

import jayden.demo.stock_price_monitor.models.prices.Price;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PriceGenerator {

    private static final Random random = new Random();
    @Autowired
    private TickerService tickerService;

    public Price generate(Price latestPrice) {
        int index = tickerService.findById(latestPrice.getTickerId()).getPriceIndex();
        double amount = latestPrice.getAmount();
        boolean up = random.nextInt(index) < 5;
        amount = up ? amount * (1 + (double) (1 + random.nextInt(index)) / 100) :
                amount * (1 - (double) (1 + random.nextInt(index)) / 100);
        return new Price(latestPrice.getTickerId(), amount);
    }
}
