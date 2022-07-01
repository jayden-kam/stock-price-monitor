package jayden.demo.stock_price_monitor.services;

import jayden.demo.stock_price_monitor.models.prices.Price;
import jayden.demo.stock_price_monitor.models.prices.PriceService;
import jayden.demo.stock_price_monitor.models.sources.Source;
import jayden.demo.stock_price_monitor.models.sources.SourceService;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class PriceSourceService {

    @Autowired
    private SourceService sourceService;
    @Autowired
    private TickerService tickerService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private PriceGenerator priceGenerator;

    public void sourcing(Source source) {
        if (source == null) {
            return;
        }
        tickerService.findBySourceId(source.getId()).forEach(ticker -> {
            Price latestPrice = priceService.findByTickerIdAndLatestOne(ticker.getId());
            priceService.add(priceGenerator.generate(latestPrice));
        });
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void runFirstSource() {
        sourcing(sourceService.findById(1));
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void runSecondSource() {
        sourcing(sourceService.findById(2));
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    public void runThirdSource() {
        sourcing(sourceService.findById(3));
    }
}
