package es.home.example.test.client;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import es.home.ws.example.Book;

public class AutoValueTest {

    @Test
    public void metodo() {
        Book book = Book.create("The stars my destination", "Alfred Bester", 214);
        assertThat(book.name(), equalTo("The stars my destination"));
        assertThat(book.author(), equalTo("Alfred Bester"));
        assertThat(book.pages(), equalTo(214));

        assertThat(Book.create("The stars my destination", "Alfred Bester", 214), equalTo(book));
        assertThat("Book{name=The stars my destination, author=Alfred Bester, pages=214}", equalTo(book.toString()));
    }

}
