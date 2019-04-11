package test.es.home.sample.wordCounter.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import es.home.sample.wordCounter.Application;
import es.home.sample.wordCounter.controller.TextController;
import es.home.sample.wordCounter.pojo.TextResponse;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { Application.class })
public class TextControllerTest {

    private static final Log LOGGER = LogFactory.getLog(TextControllerTest.class);

    @Autowired
    private TextController controller;

    @Test
    public void getText() {
        Integer start = ThreadLocalRandom.current().nextInt(1, 10);
        Integer end = ThreadLocalRandom.current().nextInt(15, 25);
        Integer countMin = ThreadLocalRandom.current().nextInt(1, 10);
        Integer countMax = ThreadLocalRandom.current().nextInt(15, 25);

        try {
            TextResponse response = controller.getText(start, end, countMin, countMax);
            assertThat(response, notNullValue());
        } catch (IOException except) {
            LOGGER.error(except.getMessage(), except);
        }
    }

    @Test
    public void getTextStartAfterEnd() {
        Integer start = 10;
        Integer end = 1;
        Integer countMin = ThreadLocalRandom.current().nextInt(1, 10);
        Integer countMax = ThreadLocalRandom.current().nextInt(15, 25);

        try {
            TextResponse response = controller.getText(start, end, countMin, countMax);
            assertThat(response, notNullValue());
            assertThat(response.getApSize(), equalTo(0.0));
        } catch (IOException except) {
            LOGGER.error(except.getMessage(), except);
        }
    }

    @Test
    public void getTextCountMinGreatherThanCountMax() {
        Integer start = ThreadLocalRandom.current().nextInt(1, 10);
        Integer end = ThreadLocalRandom.current().nextInt(15, 25);
        Integer countMin = 10;
        Integer countMax = 1;

        try {
            TextResponse response = controller.getText(start, end, countMin, countMax);
            assertThat(response, notNullValue());
            assertThat(response.getApSize(), equalTo(0.0));
        } catch (IOException except) {
            LOGGER.error(except.getMessage(), except);
        }
    }

}
