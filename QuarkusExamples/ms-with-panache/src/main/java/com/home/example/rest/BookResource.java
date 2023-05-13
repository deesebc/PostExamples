package com.home.example.rest;

import java.util.List;

import com.home.example.entity.Book;
import com.home.example.mapper.BookMapper;
import com.home.example.pojo.Success;
import com.home.example.repository.BookRepository;

import io.netty.util.internal.StringUtil;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/book")
public class BookResource {
    @Inject
    BookRepository repository;

    @Inject
    BookMapper mapper;

    //warning: if we dont add quarkus-resteasy-reactive-jackson dependency, we can not produces or consumes JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllSorted(@QueryParam("sortedBy") final String sortedBy, @QueryParam("order") final Direction order) {
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
        return repository.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Success deleteById(@PathParam("id") final Long id) {
        return new Success(Boolean.toString(repository.deleteById(id)));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book update(final Book newBook) {
        Book stored = repository.findById(newBook.getId());
        mapper.update(stored, newBook);
        repository.persist(stored);
        return stored;
    }

    //warning: if we don put @Transactional generates an error
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Book persist(final Book newBook) {
        repository.persist(newBook);
        return newBook;
    }
}
