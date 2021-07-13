package es.home.example.feignexample.client;

import java.util.List;

import es.home.example.feignexample.customize.CustomException;
import es.home.example.feignexample.pojo.Book;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Accept: application/json")
public interface BookClient {

	@RequestLine("POST")
	@Headers("Content-Type: application/json")
	void create(Book book);

	@RequestLine("GET")
	List<Book> findAll();

	@RequestLine("GET /{id}")
	Book findById(@Param("id") Integer id) throws CustomException;

	@RequestLine("DELETE /{id}")
	void remove(@Param("id") Integer id);

	@RequestLine("PUT /{id}")
	@Headers("Content-Type: application/json")
	void update(@Param("id") Integer id, Book book);
}
