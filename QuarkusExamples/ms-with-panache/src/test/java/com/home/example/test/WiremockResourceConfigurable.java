package com.home.example.test;

import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;

import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResourceConfigurableLifecycleManager;

public class WiremockResourceConfigurable implements QuarkusTestResourceConfigurableLifecycleManager<WiremockTestAnnotation> {
	
	public static WireMockServer server;
	private String port;

	@Override
	public void init(WiremockTestAnnotation params) {
		port = params.port();
	}

	@Override
	public Map<String, String> start() {
		Log.info("WiremockResourceConfigurable start");
		Log.info("WiremockResourceConfigurable port: "+port);
		server = new WireMockServer(Integer.valueOf(port));
		server.start();
		return  Map.of(
      "quarkus.rest-client.\"com.home.example.service.ExternalService\".url", "http://localhost:"+port
  );
	}

	@Override
	public void stop() {
		if (server != null) {
			server.stop();
		}
	}
}
