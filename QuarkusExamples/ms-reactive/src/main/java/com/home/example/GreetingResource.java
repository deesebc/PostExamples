package com.home.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("greeting")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    // public GreetingResource(GreetingService greetingService) {
    //     this.greetingService = greetingService;
    // }

    @GET
    @Produces("text/plain")
    public String greet() {
        return greetingService.greet();
    }
}
