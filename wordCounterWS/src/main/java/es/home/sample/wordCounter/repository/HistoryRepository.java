package es.home.sample.wordCounter.repository;

import java.util.List;

import es.home.sample.wordCounter.entity.History;

public interface HistoryRepository extends GenericDao<History, Integer> {
    List<History> findTop10ByOrderByCreatedDateDesc();
}