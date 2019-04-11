package es.home.sample.wordCounter.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import es.home.sample.wordCounter.function.HistoryFromTextResponse;
import es.home.sample.wordCounter.pojo.ReadmeResponse;
import es.home.sample.wordCounter.pojo.TextResponse;
import es.home.sample.wordCounter.repository.HistoryRepository;
import es.home.sample.wordCounter.util.RestTemplateCaller;
import es.home.sample.wordCounter.util.WordCounter;

@RestController
@RequestMapping("/wordCounter/text")
public class TextController {
    private static final Log LOGGER = LogFactory.getLog(TextController.class);

    @Autowired
    HistoryRepository repository;

    @ExceptionHandler({ IOException.class, HttpClientErrorException.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Readme API is not available")
    public void handleReadmeException(final HttpServletRequest req, final Exception except) {
        LOGGER.info("TextController.handleReadmeError called");
        LOGGER.info(except.getMessage(), except);
    }

    @GetMapping
    public TextResponse getText(@RequestParam("start") final Integer start, @RequestParam("end") final Integer end,
            @RequestParam("countMin") final Integer countMin, @RequestParam("countMax") final Integer countMax)
            throws IOException {
        LOGGER.info("TextController.getText called");
        LOGGER.info("Parameters -> start: " + start + ", end: " + end + " countMin: " + countMin + ", countMax: " + countMax);
        int actualCount = start;
        List<Long> appTimeList = new ArrayList<>();
        List<Long> apSizeList = new ArrayList<>();
        TextResponse response = new TextResponse();
        Long tpMillisInit = System.currentTimeMillis();
        if (start <= end && countMin <= countMax) {
            Long ppMillisInit = null;
            Map<String, Integer> wordCounter = new HashMap<>();
            while (actualCount <= end) {
                ppMillisInit = System.currentTimeMillis();
                ResponseEntity<ReadmeResponse> rResponse = RestTemplateCaller.readmeGibberish(actualCount, countMin, countMax);
                if (HttpStatus.OK == rResponse.getStatusCode()) {
                    WordCounter.calculateTextResponse(rResponse.getBody().getTextOut(), apSizeList, wordCounter);
                }
                appTimeList.add(System.currentTimeMillis() - ppMillisInit);
                actualCount++;
            }
            response.setFreqWord(wordCounter.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey());
        }
        response.setAppTime(Precision.round(appTimeList.stream().mapToLong(value -> value).average().orElse(0.0), 2));
        response.setApSize(Precision.round(apSizeList.stream().mapToLong(value -> value).average().orElse(0.0), 2));
        response.setTpTime(System.currentTimeMillis() - tpMillisInit);
        repository.save(HistoryFromTextResponse.INSTANCE.apply(response));
        return response;
    }

}