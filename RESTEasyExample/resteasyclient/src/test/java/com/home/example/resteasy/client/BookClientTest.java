package com.home.example.resteasy.client;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.home.example.resteasy.bean.Book;

import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@ExtendWith(SystemStubsExtension.class)
public class BookClientTest {

    @SystemStub
    private static EnvironmentVariables environmentVariables;

    @BeforeAll
    public static void init() {
	environmentVariables.set("BOOKWS_URL", "http://localhost:8080/RestEasyService/library/");
    }

    @Test
    public void read_proxy() {
	BookClient client = new BookClient();
	Book book = client.read_proxy(1);
	assertThat("Ender's Game", equalTo(book.getName()));
    }

    @Test
    public void read_target() {
	BookClient client = new BookClient();
	Book book = client.read_target(1);
	assertThat("Ender's Game", equalTo(book.getName()));
    }
}
