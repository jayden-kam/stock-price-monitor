package jayden.demo.stock_price_monitor.models.tickers;

import jayden.demo.stock_price_monitor.models.prices.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TickerServiceImpl implements TickerService {

    private static final int DEFAULT_CAPACITY = 10;
    private final List<Ticker> storage = new ArrayList<>(DEFAULT_CAPACITY);
    private int index = 0;

    @Autowired
    private PriceService priceService;

    public TickerServiceImpl() {
    }

    public TickerServiceImpl(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public void add(Ticker ticker) {
        if (ticker.getId() != -1) {
            throw new RuntimeException("!!!Cannot add ticker already exist, ticker.id=" + ticker.getId());
        }
        ticker.setId(++index);
        storage.add(ticker);
    }

    @Override
    public List<Ticker> findAll() {
        storage.forEach(ticker -> ticker.setPrices(priceService.findByTickerId(ticker.getId())));
        List<Ticker> result = new ArrayList<>(storage.size());
        result.addAll(storage);
        return result;
    }

    @Override
    public List<Ticker> findBySourceId(int sourId) {
        storage.stream().filter(ticker -> ticker.getSourceId() == sourId)
                .forEach(ticker -> ticker.setPrices(priceService.findByTickerId(ticker.getId())));
        return storage.stream().filter(ticker -> ticker.getSourceId() == sourId).collect(Collectors.toList());
    }

    @Override
    public Ticker findByIdAndSourceId(int id, int sourceId) {
        Ticker ticker = storage.stream().filter(t -> t.getId() == id && t.getSourceId() == sourceId).findFirst().orElse(null);
        if (ticker != null) {
            ticker.setPrices(priceService.findByTickerId(ticker.getId()));
        }
        return ticker;
    }
}
