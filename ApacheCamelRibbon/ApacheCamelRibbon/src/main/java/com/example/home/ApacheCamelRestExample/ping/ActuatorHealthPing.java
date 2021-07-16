package com.example.home.ApacheCamelRestExample.ping;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ActuatorHealthPing implements IPing {
    @Override
    public boolean isAlive(final Server server) {
	server.setAlive(false);
	String urlStr = String.format("http://%s/actuator/health", server.getId());
	boolean isAlive = false;
	try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
	    HttpResponse response = httpClient.execute(new HttpGet(urlStr));
	    String content = EntityUtils.toString(response.getEntity());
	    if (content != null && "UP".equals(new ObjectMapper().readTree(content).path("status").asText())) {
		isAlive = true;
	    }
	} catch (IOException e) {
	    log.warn("Error while get server status: " + e.getMessage());
	}
	server.setAlive(isAlive);
	return isAlive;
    }
}
