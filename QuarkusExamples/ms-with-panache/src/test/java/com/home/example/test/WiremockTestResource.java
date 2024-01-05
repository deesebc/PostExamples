package com.home.example.test;

import java.util.HashMap;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class WiremockTestResource implements QuarkusTestResourceLifecycleManager {
  public static WireMockServer wireMockServer;

  @Override
  public Map<String, String> start() {
	  System.out.println("WiremockTestResource start");
    wireMockServer = new WireMockServer(57001);
    wireMockServer.start();
    System.out.println("2: "+wireMockServer.getOptions().portNumber());
    System.out.println("2B: "+WiremockTestResource.wireMockServer.getOptions().portNumber());
    return new HashMap<String, String>();
  }

  @Override
  public void stop() {
    if (wireMockServer != null) {
      wireMockServer.stop();
    }
  }
}
