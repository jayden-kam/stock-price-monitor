package jayden.demo.stock_price_monitor.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jayden.demo.stock_price_monitor.models.prices.Price;
import jayden.demo.stock_price_monitor.models.prices.PriceService;
import jayden.demo.stock_price_monitor.models.sources.Source;
import jayden.demo.stock_price_monitor.models.sources.SourceService;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class PriceSourceService {

    private Logger log = LoggerFactory.getLogger(PriceSourceService.class);

    @Autowired
    private SourceService sourceService;
    @Autowired
    private TickerService tickerService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private PriceGenerator priceGenerator;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void sourcing(Source source) {
        if (source == null) {
            return;
        }
        tickerService.findBySourceId(source.getId()).forEach(ticker -> {
            List<Price> prices = priceService.findByTickerIdAndLatest(ticker.getId(), 5);
            Price latestPrice = prices.get(0);
            Price newPrice = priceGenerator.generate(latestPrice);
            priceService.add(newPrice);
            prices.add(0, newPrice);
            prices.remove(5);
            try {
                messagingTemplate.convertAndSend("/topic/update-" + ticker.getId(),
                        objectMapper.writeValueAsString(prices));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            log.info("Ticker [" + ticker.getName() + "] generate new price succeed!");
        });
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void runFirstSource() {
        sourcing(sourceService.findById(1));
    }

    @Scheduled(fixedDelay = 5*1000, initialDelay = 5*1000)
    public void runSecondSource() {
        sourcing(sourceService.findById(2));
    }

    @Scheduled(fixedDelay = 10*1000, initialDelay = 10*1000)
    public void runThirdSource() {
        sourcing(sourceService.findById(3));
    }
}
