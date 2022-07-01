package jayden.demo.stock_price_monitor.sources;

import jayden.demo.stock_price_monitor.tickers.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SourceServiceImpl implements SourceService {

    private static final int DEFAULT_CAPACITY = 3;
    private final List<Source> storage = new ArrayList<>(DEFAULT_CAPACITY);
    private int index = 0;

    @Autowired
    private TickerService tickerService;

    public SourceServiceImpl() {
    }

    public SourceServiceImpl(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @Override
    public void add(Source source) {
        if (source.getId() != -1) {
            throw new RuntimeException("!!! Cannot add source already exist, source.id=" + source.getId());
        }
        source.setId(++index);
        storage.add(source);
    }

    @Override
    public List<Source> findAll() {
        storage.forEach(source -> source.setTickers(tickerService.findBySourceId(source.getId())));
        List<Source> result = new ArrayList<>(storage.size());
        result.addAll(storage);
        return result;
    }

    @Override
    public Source findById(int id) {
        Source source = storage.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        if (source != null) {
            source.setTickers(tickerService.findBySourceId(source.getId()));
        }
        return source;
    }
}
