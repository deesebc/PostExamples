package com.home.example.springwebflux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookWebController {

    // https://mkyong.com/spring-boot/spring-boot-webflux-server-sent-events-example/

    @GetMapping("/")
    public String index(final Model model) {
	return "index";
    }

}
