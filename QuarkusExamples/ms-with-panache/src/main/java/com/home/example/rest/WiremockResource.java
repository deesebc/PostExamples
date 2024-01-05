package com.home.example.rest;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.home.example.entity.Movie;
import com.home.example.service.ExternalService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/wiremock")
@Produces(MediaType.APPLICATION_JSON)
public class WiremockResource {

	@Inject
	@RestClient
    ExternalService service;
 

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Movie getMovieById(@PathParam("id") final Long id) {
        return service.getMovieById(id);
    }
}
