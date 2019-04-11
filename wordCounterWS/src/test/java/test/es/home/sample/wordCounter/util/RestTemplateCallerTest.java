package test.es.home.sample.wordCounter.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import es.home.sample.wordCounter.Application;
import es.home.sample.wordCounter.pojo.ReadmeResponse;
import es.home.sample.wordCounter.util.RestTemplateCaller;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = { Application.class })
public class RestTemplateCallerTest {

    @Test
    public void readmeGibberish() {
        Integer nParagraphs = ThreadLocalRandom.current().nextInt(1, 10);
        Integer minWords = ThreadLocalRandom.current().nextInt(1, 10);
        Integer maxWords = ThreadLocalRandom.current().nextInt(15, 25);
        ResponseEntity<ReadmeResponse> rResponse = RestTemplateCaller.readmeGibberish(nParagraphs, minWords, maxWords);
        assertThat(rResponse.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
