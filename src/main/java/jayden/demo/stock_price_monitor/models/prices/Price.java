package jayden.demo.stock_price_monitor.models.prices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Price implements Comparable<Price> {

    private int id;
    private int tickerId;
    @JsonIgnore
    private double value;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private String amount;

    public Price(int tickerId, double value) {
        this.id = -1;
        this.tickerId = tickerId;
        this.value = value;
        this.timestamp = new Date();
    }

    @Override
    public int compareTo(Price o) {
        return Long.compare(o.timestamp.getTime(), this.timestamp.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTickerId() {
        return tickerId;
    }

    public void setTickerId(int tickerId) {
        this.tickerId = tickerId;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAmount() {
        return new BigDecimal(this.value).setScale(2, RoundingMode.HALF_UP).toString();
    }
}
