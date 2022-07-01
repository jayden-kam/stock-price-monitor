package jayden.demo.stock_price_monitor.models.prices;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriceService {

    void add(Price price);

    List<Price> findAll();

    List<Price> findByTickerId(int tickerId);
}
