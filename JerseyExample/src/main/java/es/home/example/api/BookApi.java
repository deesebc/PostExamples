package es.home.example.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.home.example.pojo.Book;
import es.home.example.pojo.Library;

@Path("/book")
public class BookApi {

	private static Map<Integer, Book> bookMap;

	static {
		bookMap = new HashMap<>();
		bookMap.put(1, new Book(1, "Isaac Asimov", "Foundation"));
		bookMap.put(2, new Book(2, "Alfred Bester", "The stars my destination"));
		bookMap.put(3, new Book(3, "Orson Scott Card", "Ender's game"));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Book> getAll() {
		return bookMap.values();
	}
	// http://localhost:8080/JerseyExample/rest/book
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Library getLibrary() {
		Library library =  new Library();
		library.getLibrary().addAll(bookMap.values());
		return library;
	}
	// http://localhost:8080/JerseyExample/rest/book

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getOne(@PathParam("id") final Integer id) {
		return bookMap.get(id);
	}
	// http://localhost:8080/JerseyExample/rest/book/2

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOne(@PathParam("id") final Integer id) {
		bookMap.remove(id);
		return Response.status(200).build();
	}
	// http://localhost:8080/JerseyExample/rest/book/2

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOne(@PathParam("id") final Integer id, final Book book) {
		bookMap.put(id, book);
		return Response.status(200).build();
	}
	// http://localhost:8080/JerseyExample/rest/book/2 . Body:
	// {"id":4,"author":"Frederik Pohl","name":"Gateway"}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveOne(final Book book) {
		bookMap.put(bookMap.size() + 1, book);
		return Response.status(200).build();
	}
	// http://localhost:8080/JerseyExample/rest/book . Body:
	// {"id":4,"author":"Frederik Pohl","name":"Gateway"}

}
