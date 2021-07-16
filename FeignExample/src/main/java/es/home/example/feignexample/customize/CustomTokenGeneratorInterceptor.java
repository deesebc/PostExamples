package es.home.example.feignexample.customize;

import java.util.Base64;

import com.google.gson.Gson;

import es.home.example.feignexample.pojo.Token;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CustomTokenGeneratorInterceptor implements RequestInterceptor {
	private Token token;

	public CustomTokenGeneratorInterceptor(final String clientId, final String clientSecret) {
		token = new Token(clientId, clientSecret);
	}

	@Override
	public void apply(final RequestTemplate template) {
		template.header("X-Auth-Token", Base64.getEncoder().encodeToString(new Gson().toJson(token).getBytes()));
	}
}
