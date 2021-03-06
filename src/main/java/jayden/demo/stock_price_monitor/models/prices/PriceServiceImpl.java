package jayden.demo.stock_price_monitor.models.prices;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PriceServiceImpl implements PriceService {

    private static final int DEFAULT_CAPACITY = 10000;
    private final List<Price> storage = new ArrayList<>(DEFAULT_CAPACITY);
    private int index = 0;

    @Override
    public void add(Price price) {
        if (price.getId() != -1) {
            throw new RuntimeException("!!!Cannot add price already exist, price.id=" + price.getId());
        }
        price.setId(++index);
        storage.add(price);
    }

    @Override
    public List<Price> findAll() {
        List<Price> result = new ArrayList<>(storage.size());
        result.addAll(storage);
        return result;
    }

    @Override
    public List<Price> findByTickerId(int tickerId) {
        return storage.stream().filter(price -> price.getTickerId() == tickerId).collect(Collectors.toList());
    }

    @Override
    public List<Price> findByTickerIdAndLatest(int tickerId, int size) {
        List<Price> result = findByTickerId(tickerId).stream().sorted().collect(Collectors.toList());
        return result.size() > size ? result.subList(0, size) : result;
    }

    @Override
    public Price findByTickerIdAndLatestOne(int tickerId) {
        List<Price> result = findByTickerIdAndLatest(tickerId, 1);
        return result.size() > 0 ? result.get(0) : null;
    }
}
