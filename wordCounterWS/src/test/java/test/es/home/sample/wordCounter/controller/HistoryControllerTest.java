package test.es.home.sample.wordCounter.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import es.home.sample.wordCounter.Application;
import es.home.sample.wordCounter.controller.HistoryController;
import es.home.sample.wordCounter.entity.History;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { Application.class })
public class HistoryControllerTest {

    @Autowired
    private HistoryController controller;

    @Test
    public void getHistory() {
        List<History> response = controller.getHistory();
        assertThat(response.size(), equalTo(0));
    }

}
