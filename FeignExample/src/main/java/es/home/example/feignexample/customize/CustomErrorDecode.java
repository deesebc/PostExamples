package es.home.example.feignexample.customize;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecode implements ErrorDecoder {
	@Override
	public Exception decode(final String methodKey, final Response response) {
		if (response.status() > 399) {
			return new CustomException("Error detected in backend");
		} else {
			return new Default().decode(methodKey, response);
		}
	}
}
