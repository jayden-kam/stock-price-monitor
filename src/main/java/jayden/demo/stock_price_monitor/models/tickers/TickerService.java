package jayden.demo.stock_price_monitor.models.tickers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TickerService {

    void add(Ticker ticker);

    List<Ticker> findAll();

    List<Ticker> findBySourceId(int sourceId);

    Ticker findById(int id);
}
