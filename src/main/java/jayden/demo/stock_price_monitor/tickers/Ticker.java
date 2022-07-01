package jayden.demo.stock_price_monitor.tickers;

import jayden.demo.stock_price_monitor.prices.Price;

import java.util.List;

public class Ticker {

    private int id;
    private int sourceId;
    private String name;
    private List<Price> prices;

    public Ticker(int sourceId, String name) {
        this.id = -1;
        this.sourceId = sourceId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
