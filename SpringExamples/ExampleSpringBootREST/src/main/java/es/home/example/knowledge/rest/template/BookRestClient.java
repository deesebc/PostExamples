package es.home.example.knowledge.rest.template;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.home.example.knowledge.entity.Book;

@Service
public class BookRestClient {
	public static final String REST_SERVICE_URI = "http://localhost:8085";
	public Book getBook(final Integer id) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(REST_SERVICE_URI + "/book/" + id, Book.class);
	}
}
