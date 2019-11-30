package com.home.example.wiremockhttps.main;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.home.example.wiremockhttps.helper.HttpClientHelper;
import com.home.example.wiremockhttps.pojo.Token;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RunWith(JUnitPlatform.class)
public class WiremockTest {

    private final static String FOLDER = "src/test/resources/wiremock";

    WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().withRootDirectory(FOLDER).port(57002).httpsPort(57005)
                .keystorePath(FOLDER + "/identity.jks").notifier(new ConsoleNotifier(true)));
        wireMockServer.start();
    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void getToken() {
        log.info("Init");
        try {
            wireMockServer.stubFor(post(urlPathEqualTo("/token")).withQueryParam("grant_type", WireMock.equalTo("client_credentials"))
                    .withHeader("Authorization", WireMock.equalTo("Basic MTIzNDo0MzIx")).willReturn(aResponse().withBodyFile("tokenCredentialsResponse.json")));
            log.info("Wiremock configured");
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Basic MTIzNDo0MzIx");
            String sEndpoint = "https://localhost:57005/token?grant_type=client_credentials";
            Token token = HttpClientHelper.doPost(sEndpoint, headers, Token.class);
            assertThat(token, is(notNullValue()));
            assertThat(token.getAccessToken(), is(equalTo("123456")));
        } catch (IOException except) {
            log.error(except.getMessage(), except);
            Assert.fail();
        }
    }
}
