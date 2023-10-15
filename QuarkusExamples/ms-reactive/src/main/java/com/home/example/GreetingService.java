package com.home.example;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
    public String greet(){
        return "hello";
    }
}