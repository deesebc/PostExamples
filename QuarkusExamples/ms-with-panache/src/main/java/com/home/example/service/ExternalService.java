package com.home.example.service;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.home.example.entity.Movie;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/imdb")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
public interface ExternalService {

    @GET
    @Path("/film/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Movie getMovieById(@PathParam("id") Long id);
}