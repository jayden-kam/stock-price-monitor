package jayden.demo.stock_price_monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class StockPriceMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockPriceMonitorApplication.class, args);
    }

    @Bean
    public ObjectMapper buildObjectMapper() {
        return new Jackson2ObjectMapperBuilder().build();
    }
}
