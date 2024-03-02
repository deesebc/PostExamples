package com.home.example.resource;

import java.util.List;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.home.example.entity.Book;
import com.home.example.repository.BookRepository;

import io.netty.util.internal.StringUtil;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/book")
public class BookResource {

	@ConfigProperty(name = "log.message")
	String message;

	@Inject
	BookRepository repository;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> getAllSorted(@QueryParam("sortedBy") final String sortedBy,
			@QueryParam("order") final Direction order) {
		Log.info("Message from application.properties: " + message);
		Log.info(String.format("getAllSorted: sortedBy %s order %s", sortedBy, order));
		List<Book> retorno;
		if (!StringUtil.isNullOrEmpty(sortedBy) && !StringUtil.isNullOrEmpty(sortedBy)) {
			retorno = repository.listAll(Sort.by(sortedBy, order));
		} else if (!StringUtil.isNullOrEmpty(sortedBy)) {
			retorno = repository.listAll(Sort.by(sortedBy));
		} else {
			retorno = repository.listAll();
		}
		return retorno;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBookById(@PathParam("id") final Long id) {
		Log.info("Message: " + message);
		return repository.findById(id);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteById(@PathParam("id") final Long id) {
		if (repository.deleteById(id)) {
			return Response.status(201).entity("Ok").build();
		}
		return Response.status(400).entity(String.format("error deleting %d", id)).build();
	}

//	// warning: if we dont put @Transactional findById and persist have different
//	// transaction and generates error:
//	// org.hibernate.PersistentObjectException: detached entity passed to persist
//	@PUT
//	@Produces(MediaType.APPLICATION_JSON)
//	@Transactional
//	public Book update(final Book newBook) {
//		Book stored = repository.findById(newBook.getId());
//		mapper.update(stored, newBook);
//		repository.persist(stored);
//		return stored;
//	}

	// warning: if we don put @Transactional generates an error. In this case is in
	// Repository
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persist(final Book newBook) {
		repository.persist(newBook);
		return Response.status(201).entity(newBook).build();
	}
}
