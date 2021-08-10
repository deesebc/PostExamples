package es.home.example.sparkexample.microservice;

import static spark.Spark.get;
import static spark.Spark.port;

import com.google.gson.Gson;

import es.home.example.sparkexample.bean.Msg;

public class HelloWorld {
	public static void main(final String[] args) {
		port(8080);
		get("/hello", (req, res) -> {
			res.type("application/json");
			return new Gson().toJson(new Msg("Hello World!"));
		});
	}
}
