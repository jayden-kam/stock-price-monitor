package jayden.demo.stock_price_monitor.services;

import jayden.demo.stock_price_monitor.models.sources.Source;
import jayden.demo.stock_price_monitor.models.sources.SourceService;
import jayden.demo.stock_price_monitor.models.sources.SourceServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SourceServiceTest {

    private SourceService sourceService;

    @Before
    public void init() {
        sourceService = new SourceServiceImpl();
        sourceService.add(new Source("X"));
        sourceService.add(new Source("Y"));
    }

    @Test
    public void addSource_isSuccess() {
        sourceService.add(new Source("Z"));
        Assert.assertEquals(3, sourceService.findAll().size());
    }

    @Test
    public void addSource_isFailed() {
        Source invalidSource = new Source("Z");
        invalidSource.setId(3);
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> sourceService.add(invalidSource));
        Assert.assertTrue(exception.getMessage().contains("Cannot add source already exist"));
    }

    @Test
    public void findById_isSuccess() {
        Assert.assertEquals("X", sourceService.findById(1).getName());
        Assert.assertEquals("Y", sourceService.findById(2).getName());
    }
}
