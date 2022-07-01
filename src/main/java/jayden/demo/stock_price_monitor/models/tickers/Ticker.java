package jayden.demo.stock_price_monitor.models.tickers;

import jayden.demo.stock_price_monitor.models.prices.Price;

import java.util.List;

public class Ticker {

    private int id;
    private int sourceId;
    private String name;
    private int priceIndex;
    private List<Price> prices;

    public Ticker(int sourceId, String name) {
        this(sourceId, name, -1);
    }

    public Ticker(int sourceId, String name, int priceIndex) {
        this.id = -1;
        this.sourceId = sourceId;
        this.name = name;
        this.priceIndex = priceIndex;
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

    public int getPriceIndex() {
        return priceIndex;
    }

    public void setPriceIndex(int priceIndex) {
        this.priceIndex = priceIndex;
    }
}
