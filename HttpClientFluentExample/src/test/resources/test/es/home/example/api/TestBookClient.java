package test.es.home.example.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.google.gson.Gson;

import es.home.example.api.BookClient;
import es.home.example.pojo.Book;

public class TestBookClient {

	BookClient client = new BookClient();

	@Test
	public void getAll() throws ClientProtocolException, IOException {
		List<Book> list = client.getAll();
		assertThat(list.size(), equalTo(3));
	}

	@Test
	public void getOne() throws ClientProtocolException, IOException {
		Book two = client.getOne(2);
		assertThat(two.getId(), equalTo(2));
		assertThat(two.getName(), equalTo("The stars my destination"));
	}

	@Test
	public void saveOne() throws ClientProtocolException, IOException {
		Book book = new Book(4, "F. Herbert", "Dune");
		int returnCode = client.saveOne(book);
		assertThat(returnCode, equalTo(200));
	}

	@Test
	public void updateOne() throws ClientProtocolException, IOException {
		Book book = new Book(4, "Frank Herbert", "Dune");
		int returnCode = client.updateOne(4, book);
		assertThat(returnCode, equalTo(200));
		Book two = client.getOne(4);
		assertThat(two.getId(), equalTo(4));
		assertThat(two.getAuthor(), equalTo("Frank Herbert"));
	}

	@Test
	public void deleteOne() throws ClientProtocolException, IOException {
		int returnCode = client.deleteOne(4);
		assertThat(returnCode, equalTo(200));
		List<Book> list = client.getAll();
		assertThat(list.size(), equalTo(3));
	}

}
