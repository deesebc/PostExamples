package test.es.home.example.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import es.home.example.api.BookClient;
import es.home.example.pojo.Book;

public class TestBookClient {

	BookClient client = new BookClient();
	
	@Test
	public void getAll() throws ClientProtocolException, IOException{
		List<Book> list = client.getAll();
		assertThat(list.size(), equalTo(3));
	}
	
	@Test
	public void getOne() throws ClientProtocolException, IOException{
		Book two = client.getOne(2);
		assertThat(two.getId(), equalTo(2));
	}
	


}
