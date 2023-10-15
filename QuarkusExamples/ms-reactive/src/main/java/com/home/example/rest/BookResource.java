package com.home.example.rest;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import com.home.example.entity.Book;
import com.home.example.mapper.BookMapper;
import com.home.example.pojo.Success;
import com.home.example.repository.BookRepository;

import io.netty.util.internal.StringUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.Cancellable;
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
    public Uni<List<Book>> getAllSorted(@QueryParam("sortedBy") final String sortedBy, @QueryParam("order") final Direction order) {
        Log.info(String.format("getAllSorted: sortedBy %s order %s", sortedBy, order));
        Uni<List<Book>> retorno;
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
    public Uni<Book> getBookById(@PathParam("id") final Long id) {
        return repository.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Success> deleteById(@PathParam("id") final Long id) {
       return repository.deleteById(id).onItem().transform((b) -> new Success(Boolean.toString(b)));       
    }

    //warning: if we dont put @WithTransaction findById and persist have different transaction and generates error:
    //org.hibernate.PersistentObjectException: detached entity passed to persist
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    public Uni<Book> update(final Book newBook) {
        return repository.findById(newBook.getId()).onItem().transform(stored -> mapper.copy(stored));
    }

    //warning: if we don put @WithTransaction generates an error. In this case is in Repository
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Book> persist(final Book newBook) {
        return repository.persist(newBook);
    }
}
