package com.example.home.ApacheCamelRestExample.pojo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
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
//	System.out.println("--- ESTOY EN MI PING ----");
//	System.out.println("host: " + server.getHost());
//	System.out.println("getHostPort: " + server.getHostPort());
	boolean isSecure = false;
	String urlStr = "";
	if (isSecure) {
	    urlStr = "https://";
	} else {
	    urlStr = "http://";
	}
	urlStr += server.getId();
	urlStr += "/actuator/health";

//	System.out.println("urlStr: " + urlStr);
	server.setAlive(false);
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
//	System.out.println("host: " + server.getHost());
//	System.out.println("getHostPort: " + server.getHostPort());
	server.setAlive(isAlive);
//	System.out.println("alive: " + server.isAlive());
	return isAlive;
    }

}
