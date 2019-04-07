package es.home.example.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import com.google.gson.Gson;

import es.home.example.pojo.Book;

public class BookClient {

	private final static String URL = "http://localhost:8080/JerseyExample/rest/book";

	public List<Book> getAll() throws ClientProtocolException, IOException {
		String jsonResponse = Request.Get(URL).connectTimeout(1000).socketTimeout(1000).execute().returnContent()
				.asString();
		java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<ArrayList<Book>>() {
		}.getType();
		List<Book> yourClassList = new Gson().fromJson(jsonResponse, listType);
		return yourClassList;
	}
	// http://localhost:8080/JerseyExample/rest/book

	public Book getOne(final Integer id) throws ClientProtocolException, IOException {
		String jsonResponse = Request.Get(URL + "/" + id).connectTimeout(1000).socketTimeout(1000).execute()
				.returnContent().asString();
		// jsonResponse = jsonResponse.substring(1, jsonResponse.length()-1);
		return new Gson().fromJson(jsonResponse, Book.class);
	}
	// // http://localhost:8080/JerseyExample/rest/book/2

	public int deleteOne(final Integer id) throws ClientProtocolException, IOException {
		return Request.Delete(URL + "/" + id).useExpectContinue().execute().returnResponse().getStatusLine()
				.getStatusCode();
	}
	// http://localhost:8080/JerseyExample/rest/book/2

	public int updateOne(final Integer id, final Book book) throws ClientProtocolException, IOException {
		String bookJson = new Gson().toJson(book);
		StringEntity requestEntity = new StringEntity(bookJson, ContentType.APPLICATION_JSON);
		return Request.Put(URL + "/" + id).body(requestEntity).useExpectContinue().version(HttpVersion.HTTP_1_1)
				.execute().returnResponse().getStatusLine().getStatusCode();
	}
	// http://localhost:8080/JerseyExample/rest/book/2 . Body:
	// {"id":4,"author":"Frederik Pohl","name":"Gateway"}

	public int saveOne(final Book book) throws ClientProtocolException, IOException {
		String bookJson = new Gson().toJson(book);
		return Request.Post(URL).bodyString(bookJson, ContentType.APPLICATION_JSON).execute().returnResponse()
				.getStatusLine().getStatusCode();
	}
	// http://localhost:8080/JerseyExample/rest/book . Body:
	// {"id":4,"author":"Frederik Pohl","name":"Gateway"}

}
