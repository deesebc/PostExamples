/*
 */
package es.home.example.spark;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import es.home.example.spark.pojo.Book;
import es.home.example.spark.transformer.JsonTransformer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    private static Map<Integer, Book> bookMap;

    static {
        bookMap = new HashMap<>();
        bookMap.put(1, new Book(1, "Isaac Asimov", "Foundation"));
        bookMap.put(2, new Book(2, "Alfred Bester", "The stars my destination"));
        bookMap.put(3, new Book(3, "Orson Scott Card", "Ender's game"));
    }

    public static void main(final String[] args) {
        port(8080);
        staticFiles.expireTime(600L);

        path("/book", () -> {
            before("/*", (q, a) -> log.info("Received api call"));
            get("", (req, res) -> new Gson().toJson(bookMap.values().stream().collect(Collectors.toList())));
            get("/:id", (req, res) -> bookMap.get(Integer.valueOf(req.params(":id"))), new JsonTransformer());
            post("/:id", (request, response) -> {
                Integer id = Integer.valueOf(request.params(":id"));
                Book book = new Gson().fromJson(request.body(), Book.class);
                bookMap.put(id, book);
                return new Gson().toJson(book);
            });
            put("/:id", (request, response) -> {
                Integer id = Integer.valueOf(request.params(":id"));
                Book book = new Gson().fromJson(request.body(), Book.class);
                bookMap.put(id, book);
                return new Gson().toJson(book);
            });
            get("/:id", (req, res) -> bookMap.get(Integer.valueOf(req.params(":id"))), new JsonTransformer());
            delete("/:id", (request, response) -> bookMap.remove(Integer.valueOf(request.params(":id"))), new Gson()::toJson);
        });
    }
}
