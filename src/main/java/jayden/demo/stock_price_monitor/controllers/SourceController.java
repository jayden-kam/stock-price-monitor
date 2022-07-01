package jayden.demo.stock_price_monitor.controllers;

import jayden.demo.stock_price_monitor.models.sources.Source;
import jayden.demo.stock_price_monitor.models.sources.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sources")
public class SourceController {

    @Autowired
    private SourceService sourceService;

    @GetMapping
    public List<Source> get() {
        return sourceService.findAll();
    }
}
