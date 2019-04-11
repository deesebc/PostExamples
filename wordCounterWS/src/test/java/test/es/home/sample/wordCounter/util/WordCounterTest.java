package test.es.home.sample.wordCounter.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import es.home.sample.wordCounter.util.WordCounter;

public class WordCounterTest {

    private static final Log LOGGER = LogFactory.getLog(WordCounterTest.class);

    @Test
    public void calculateTextResponse() {
        List<Long> apSizeList = new ArrayList<>();
        Map<String, Integer> wordCounter = new HashMap<>();
        String textOut = "<p>Rose much some squirrel.</p>\r<p>Regardless vicious goodness outside some.</p>\r<p>While then onto when far.</p>\r";
        try {
            WordCounter.calculateTextResponse(textOut, apSizeList, wordCounter);
            assertThat(apSizeList.size(), equalTo(3));
            assertThat(wordCounter.keySet().size(), equalTo(13));
        } catch (IOException except) {
            LOGGER.error(except.getMessage(), except);
            Assert.fail();
        }

    }
}
