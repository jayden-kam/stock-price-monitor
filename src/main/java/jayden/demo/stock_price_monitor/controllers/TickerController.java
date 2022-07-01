package jayden.demo.stock_price_monitor.controllers;

import jayden.demo.stock_price_monitor.models.tickers.Ticker;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tickers")
public class TickerController {

    @Autowired
    private TickerService tickerService;

    @GetMapping
    public List<Ticker> get() {
        return tickerService.findAll();
    }
}
