package com.example.home.ApacheCamelMicrometer.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class MockRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

        rest("/mockCounter").get().produces(MediaType.APPLICATION_JSON_VALUE).route()
        .to("micrometer:counter:simpleCounterMock").setBody(simple("{\"msg\":\"Hello World\"}")).unmarshal().json();

        rest("/mockTimer").get().produces(MediaType.APPLICATION_JSON_VALUE).route()
        .to("micrometer:timer:simpleTimerMock?action=start").delay(1000).setBody(simple("{\"msg\":\"Hello World\"}")).to("micrometer:timer:simpleTimerMock?action=stop").unmarshal().json();
    }

}
