package es.home.example.api;

import java.util.Collection;

import javax.ws.rs.core.Response;

import es.home.example.pojo.Book;
import es.home.example.pojo.Library;

public interface BookWS {
    Collection<Book> getAll();

    Library getLibrary();

    Book getOne(final Integer id);

    Response deleteOne(final Integer id);

    Response updateOne(final Integer id, final Book book);

    Response saveOne(final Book book);
}
