package test.es.home.example.api.rest.assured;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.mapper.TypeRef;

public class WebServiceTest {

    @Test
    public void validateBody() {
        // http://localhost:8080/JerseyExample/rest/book
        given().when().get("/JerseyExample/rest/book").then().content("id[0]", equalTo(1));
        given().when().get("/JerseyExample/rest/book").then().body("id", hasItems(1, 2, 3));
        given().when().get("/JerseyExample/rest/book/list").then().body("library.findAll { it.id < 2 }.author", hasItems("Isaac Asimov"));
    }

    @Test
    public void validateRoot() {
        get("/JerseyExample/rest/book").then().root("[0]").body("author", equalTo("Isaac Asimov")).and().body("id", equalTo(1));
    }

    @Test
    public void withParams() {
        get("/JerseyExample/rest/book/{id}", 1).then().body("author", equalTo("Isaac Asimov"));
    }

    @Test
    public void validateResponse() {
        get("/JerseyExample/rest/book").then().contentType("application/json");
        get("/JerseyExample/rest/book").then().header("Content-Type", "application/json");
        get("/JerseyExample/rest/book").then().statusCode(200);
    }

    // @Test
    // public void validateSchema() {
    // get("/JerseyExample/rest/book/1").then().assertThat().body(matchesJsonSchemaInClasspath("book-schema.json"));
    // }

    @Test
    public void validateSerilizeObject() {
        List<Map<String, Object>> books = get("/JerseyExample/rest/book").as(new TypeRef<List<Map<String, Object>>>() {
        });
        assertThat(books, hasSize(3));
        assertThat(books.get(0).get("author"), equalTo("Isaac Asimov"));
    }

    @Test
    public void gettingResponse() {
        String json = get("/JerseyExample/rest/book/1").asString();
        assertThat(json, equalTo("{\"id\":1,\"author\":\"Isaac Asimov\",\"name\":\"Foundation\"}"));

        Map<String, String> allCookies = get("/JerseyExample/rest/book/1").getCookies();
        assertThat(allCookies.size(), equalTo(0));

        String pathValue = get("/JerseyExample/rest/book/1").path("author");
        assertThat(pathValue, equalTo("Isaac Asimov"));
    }

    @Test
    public void time() {
        get("/JerseyExample/rest/book/{id}", 1).then().time(lessThan(2000L)); // Milliseconds

    }

    @Test
    public void log() {
        given().log().all().get("/JerseyExample/rest/book/{id}", 1).then().time(lessThan(2000L));
    }

}
