package com.example.demo.springeurekaclient.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientRestController {


    @GetMapping(value="/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public String index() {
        return "[{\"id\":1,\"name\":\"Ender's game\",\"author\":\"Orson Scott Card\"},{\"id\":2,\"name\":\"Foundation\",\"author\":\"Isaac Asimov\"}]";
    }
}
