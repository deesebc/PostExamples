package com.home.example.resteasy.configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/library")
public class LibraryApplication extends Application {
//    private Set<Object> singletons = new HashSet<Object>();
//
//    public LibraryApplication() {
//	singletons.add(new BookService());
//    }
//
//    @Override
//    public Set<Object> getSingletons() {
//	return singletons;
//    }
}