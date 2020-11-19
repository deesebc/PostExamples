package com.example.home.ApacheCamelEureka.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class MockRouter extends RouteBuilder {

    @Autowired
    private DiscoveryClient discoveryClient;



    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

        rest("/mockCounter").get().produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(simple("{\"msg\":\"Hello World\"}")).unmarshal().json();


        rest("/mockService").get().produces(MediaType.APPLICATION_JSON_VALUE).route()
        .serviceCall("discovery-client/books?bridgeEndpoint=true")
        //        .serviceCall("discovery-client", "undertow:http://discovery-client/books")
        //        .serviceCall("discovery-client", "undertow:discovery-client/books")
        .log("Body: ${body}")
        //        .setBody(simple("{\"msg\":\"Hello World Service\"}"))
        .unmarshal().json();

        //        System.out.println("discovery-client - url "+discoveryClient.getInstances("discovery-client").get(0).getUri());

        //bueno : http://192.168.0.19:8385/books
        //malo  : http://192.168.0.19:8385/books


    }

}
