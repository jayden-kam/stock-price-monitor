package jayden.demo.stock_price_monitor.controllers;

import jayden.demo.stock_price_monitor.models.prices.Price;
import jayden.demo.stock_price_monitor.models.prices.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping()
    public List<Price> get(@RequestParam("ticker_id") int tickerId, @RequestParam("latest") boolean latest) {
        List<Price> prices = priceService.findByTickerId(tickerId);
        Collections.sort(prices);
        if (latest && prices.size() > 5) {
            return prices.subList(0, 5);
        }
        return prices;
    }
}
