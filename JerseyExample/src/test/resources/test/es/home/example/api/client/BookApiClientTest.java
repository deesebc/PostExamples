package test.es.home.example.api.client;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.ws.rs.core.Response;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import es.home.example.api.client.BookApiClient;
import es.home.example.pojo.Book;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookApiClientTest {

    public BookApiClient client = new BookApiClient("http://localhost:8082/JerseyExample/rest/book");
    Book bookToSave = new Book(4, "Frederik Pohl", "Gateway");

    @Test
    public void test01SaveOne() {
        Response response = client.saveOne(bookToSave);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatus(), is(equalTo(200)));
    }

    @Test
    public void test02GetOne() {
        Book book = client.getOne(4);
        assertThat(book, is(notNullValue()));
        assertThat(book.getId(), is(equalTo(4)));
        assertThat(book.getName(), is(equalTo("Gateway")));
    }

    @Test
    public void test03UpdateOne() {
        bookToSave.setAuthor("Frederik G. Pohl");
        Response response = client.updateOne(4, bookToSave);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatus(), is(equalTo(200)));
    }

    @Test
    public void test04DeleteOne() {
        Response response = client.deleteOne(4);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatus(), is(equalTo(200)));
    }
}
