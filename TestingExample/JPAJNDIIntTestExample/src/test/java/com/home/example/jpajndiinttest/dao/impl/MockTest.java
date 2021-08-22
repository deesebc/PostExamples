package com.home.example.jpajndiinttest.dao.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

//you can see how this class it's execute with maven-surefire-plugin
public class MockTest {

    @Test
    public void metodo() {
	assertThat(1, equalTo(1));
    }

}
