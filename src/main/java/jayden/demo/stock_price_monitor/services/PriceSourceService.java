package jayden.demo.stock_price_monitor.services;

import jayden.demo.stock_price_monitor.models.prices.Price;
import jayden.demo.stock_price_monitor.models.prices.PriceService;
import jayden.demo.stock_price_monitor.models.sources.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceSourceService {

    @Autowired
    private PriceService priceService;
    @Autowired
    private PriceGenerator priceGenerator;

    public void sourcing(Source source) {
        source.getTickers().forEach(ticker -> {
            Price latestPrice = priceService.findByTickerIdAndLatestOne(ticker.getId());
            priceService.add(priceGenerator.generate(latestPrice));
        });
    }
}
