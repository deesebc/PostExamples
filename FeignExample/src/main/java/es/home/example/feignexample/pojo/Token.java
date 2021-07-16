package es.home.example.feignexample.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Token {

	private String clientId;
	private String clientSecret;
}
