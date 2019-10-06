package es.home.ws.example;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Book {
    public static Book create(final String name, final String author, final Integer pages) {
        return new AutoValue_Book(name, author, pages);
    }

    public abstract String name();

    public abstract String author();

    public abstract Integer pages();
}
