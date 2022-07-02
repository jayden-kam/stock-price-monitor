package jayden.demo.stock_price_monitor.services;

import jayden.demo.stock_price_monitor.models.prices.Price;
import jayden.demo.stock_price_monitor.models.prices.PriceService;
import jayden.demo.stock_price_monitor.models.sources.Source;
import jayden.demo.stock_price_monitor.models.sources.SourceService;
import jayden.demo.stock_price_monitor.models.tickers.Ticker;
import jayden.demo.stock_price_monitor.models.tickers.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DefaultCommandLineRunner implements org.springframework.boot.CommandLineRunner {

    @Autowired
    private PriceService priceService;
    @Autowired
    private TickerService tickerService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private PriceGenerator priceGenerator;

    @Override
    public void run(String... args) throws Exception {
        buildSources();
        buildTickers();
        buildPrices();
    }

    private void buildSources() {
        sourceService.add(new Source("SRC 1")); // id -> 1
        sourceService.add(new Source("SRC 2")); // id -> 2
        sourceService.add(new Source("SRC 3")); // id -> 3
    }

    private void buildTickers() {
        tickerService.add(new Ticker(1, "AAPL", 5)); // id -> 1
        tickerService.add(new Ticker(1, "MSFT", 10)); // id -> 2
        tickerService.add(new Ticker(1, "GOOG", 15)); // id -> 3
        tickerService.add(new Ticker(1, "GOOGL", 20)); // id -> 4
        tickerService.add(new Ticker(2, "AMZN", 10)); // id -> 5
        tickerService.add(new Ticker(2, "TSLA", 10)); // id -> 6
        tickerService.add(new Ticker(2, "UNH", 20)); // id -> 7
        tickerService.add(new Ticker(3, "JNJ", 10)); // id -> 8
        tickerService.add(new Ticker(3, "META", 15)); // id -> 9
        tickerService.add(new Ticker(3, "TSM", 20)); // id -> 10
    }

    private void buildPrices() {
        Price latestPrice, newPrice;

        latestPrice = new Price(1, 136.72);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(2, 256.83);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(3, 2187.45);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(4, 2179.26);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(5, 106.21);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(6, 673.42);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(7, 513.63);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(8, 177.51);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(9, 161.25);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);


        latestPrice = new Price(10, 81.75);
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);

        newPrice = priceGenerator.generate(latestPrice);
        newPrice.setTimestamp(new Date(latestPrice.getTimestamp().getTime() - 5000));
        latestPrice = newPrice;
        priceService.add(latestPrice);
    }
}
