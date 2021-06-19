package com.example.home.ApacheCamelRestExample.pojo;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

public class CustomPing implements IPing {

    @Override
    public boolean isAlive(final Server server) {
	System.out.println("--- ESTOY EN MI PING ----");
	System.out.println("alive: " + server.isAlive());
	System.out.println("host: " + server.getHost());
	System.out.println("getHostPort: " + server.getHostPort());
	// TODO Auto-generated method stub
	return server.isAlive();
    }

}
