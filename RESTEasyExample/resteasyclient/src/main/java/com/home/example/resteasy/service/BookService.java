package com.home.example.resteasy.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.home.example.resteasy.bean.Book;

@Path("/book")
public interface BookService {
    @POST
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Response create(final Book book);

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    Book read(@PathParam("id") final Integer id);
}
