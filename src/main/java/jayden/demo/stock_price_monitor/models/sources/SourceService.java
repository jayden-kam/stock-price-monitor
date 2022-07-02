package jayden.demo.stock_price_monitor.models.sources;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SourceService {

    void add(Source source);

    List<Source> findAll();

    Source findById(int id);
}
