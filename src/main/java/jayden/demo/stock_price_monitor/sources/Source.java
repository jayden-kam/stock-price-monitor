package jayden.demo.stock_price_monitor.sources;

import jayden.demo.stock_price_monitor.tickers.Ticker;

import java.util.List;

public class Source {

    private int id;
    private String name;
    private List<Ticker> tickers;

    public Source(String name) {
        this.id = -1;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticker> getTickers() {
        return tickers;
    }

    public void setTickers(List<Ticker> tickers) {
        this.tickers = tickers;
    }
}
