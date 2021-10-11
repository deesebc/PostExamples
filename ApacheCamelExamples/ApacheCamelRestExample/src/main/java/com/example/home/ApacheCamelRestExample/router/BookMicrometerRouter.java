package com.example.home.ApacheCamelRestExample.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.micrometer.MicrometerConstants;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.example.home.ApacheCamelRestExample.pojo.Book;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.util.HierarchicalNameMapper;
import io.micrometer.graphite.GraphiteConfig;
import io.micrometer.graphite.GraphiteMeterRegistry;

@Component
public class BookMicrometerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json_xml);

        rest().get("micrometer/book").produces(MediaType.APPLICATION_JSON_VALUE).route().to("sql:{{sql.selectAll}}")
        .to("micrometer:counter:simple.counter.getall")
        .log("--- SQL select all books---");

        rest().get("micrometer/book/{id}").description("Details of an book by id").outType(Book.class)
        .produces(MediaType.APPLICATION_JSON_VALUE).route().log("--- 1 select a book ${body} ---")
        .to("micrometer:timer:simple.timer.getOne?action=start")
        .to("sql:{{sql.selectById}}")
        .to("micrometer:timer:simple.timer.getOne?action=stop")
        .log("--- 2 select a book ${body} ---");

    }

    @Bean(name = MicrometerConstants.METRICS_REGISTRY_NAME)
    public MeterRegistry getMeterRegistry() {
        GraphiteConfig graphiteConfig = new GraphiteConfig() {
            @Override
            public String host() {
                return "localhost";
            }

            @Override
            public String get(final String k) {
                return null; // accept the rest of the defaults
            }
        };
        MeterRegistry registry = new GraphiteMeterRegistry(graphiteConfig, Clock.SYSTEM, HierarchicalNameMapper.DEFAULT);
        return registry;
    }

}
