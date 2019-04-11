package es.home.sample.wordCounter.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.home.sample.wordCounter.entity.History;
import es.home.sample.wordCounter.repository.HistoryRepository;

@RestController
@RequestMapping("/wordCounter/history")
public class HistoryController {

    private static final Log LOGGER = LogFactory.getLog(HistoryController.class);

    @Autowired
    HistoryRepository repository;

    @GetMapping
    public List<History> getHistory() {
        LOGGER.info("HistoryController.getHistory called");
        return repository.findTop10ByOrderByCreatedDateDesc();
    }

}