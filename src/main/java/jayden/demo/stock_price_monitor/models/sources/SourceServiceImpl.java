package jayden.demo.stock_price_monitor.models.sources;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SourceServiceImpl implements SourceService {

    private static final int DEFAULT_CAPACITY = 3;
    private final List<Source> storage = new ArrayList<>(DEFAULT_CAPACITY);
    private int index = 0;

    @Override
    public void add(Source source) {
        if (source.getId() != -1) {
            throw new RuntimeException("!!! Cannot add source already exist, source.id=" + source.getId());
        }
        source.setId(++index);
        storage.add(source);
    }

    @Override
    public List<Source> findAll() {
        List<Source> result = new ArrayList<>(storage.size());
        result.addAll(storage);
        return result;
    }

    @Override
    public Source findById(int id) {
        return storage.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }
}
