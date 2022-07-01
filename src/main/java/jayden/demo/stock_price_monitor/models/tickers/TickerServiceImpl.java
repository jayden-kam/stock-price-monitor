package jayden.demo.stock_price_monitor.models.tickers;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TickerServiceImpl implements TickerService {

    private static final int DEFAULT_CAPACITY = 10;
    private final List<Ticker> storage = new ArrayList<>(DEFAULT_CAPACITY);
    private int index = 0;

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
        List<Ticker> result = new ArrayList<>(storage.size());
        result.addAll(storage);
        return result;
    }

    @Override
    public List<Ticker> findBySourceId(int sourId) {
        return storage.stream().filter(ticker -> ticker.getSourceId() == sourId).collect(Collectors.toList());
    }

    @Override
    public Ticker findById(int id) {
        return storage.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
}
