package es.home.example.api.client;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.home.example.api.BookWS;
import es.home.example.pojo.Book;
import es.home.example.pojo.Library;

public class BookApiClient implements BookWS {

    private static String REST_URI = "http://localhost:8080/JerseyExample/rest/book";

    public BookApiClient() {
    }

    public BookApiClient(final String url) {
        REST_URI = url;
    }

    private Client client = ClientBuilder.newClient();

    @Override
    public Collection<Book> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Library getLibrary() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Book getOne(final Integer id) {
        return client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).get(Book.class);
    }

    @Override
    public Response deleteOne(final Integer id) {
        return client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).delete();
    }

    @Override
    public Response updateOne(final Integer id, final Book book) {
        return client.target(REST_URI).path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).put(Entity.entity(book, MediaType.APPLICATION_JSON));
    }

    @Override
    public Response saveOne(final Book book) {
        return client.target(REST_URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(book, MediaType.APPLICATION_JSON));
    }

}
