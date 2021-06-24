package com.example.home.ApacheCamelRestExample.ping;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ActuatorHealthPing implements IPing {

//    @Override
//    public boolean isAlive(final Server server) {
//	System.out.println("--- ESTOY EN MI PING ----");
//	System.out.println("alive: " + server.isAlive());
//	System.out.println("host: " + server.getHost());
//	System.out.println("getHostPort: " + server.getHostPort());
//	// TODO Auto-generated method stub
//	return server.isAlive();
//    }

    @Override
    public boolean isAlive(final Server server) {
	server.setAlive(false);

	String urlStr = "http://";
	urlStr += server.getId();
	urlStr += "/actuator/health";

	boolean isAlive = newMethod(urlStr);
	server.setAlive(isAlive);
	return isAlive;
    }

    private boolean newMethod(final String urlStr) {
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
	return isAlive;
    }

    private boolean oldMethod(final String urlStr) {
	boolean isAlive = false;
	HttpClient httpClient = new DefaultHttpClient();
	HttpParams httpParams = httpClient.getParams();
	httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 250);
	HttpUriRequest getRequest = new HttpGet(urlStr);
	String content = null;
	try {
	    HttpResponse response = httpClient.execute(getRequest);
	    content = EntityUtils.toString(response.getEntity());
	    isAlive = response.getStatusLine().getStatusCode() == 200;
	    if (content == null) {
		isAlive = false;
	    } else {
		String status = new ObjectMapper().readTree(content).path("status").asText();
		if ("UP".equals(status)) {
		    isAlive = true;
		} else {
		    isAlive = false;
		}
	    }
	} catch (IOException e) {
	    log.warn("Error while get server status: " + e.getMessage());
	} finally {
	    // Release the connection.
	    getRequest.abort();
	}
	return isAlive;
    }

}
