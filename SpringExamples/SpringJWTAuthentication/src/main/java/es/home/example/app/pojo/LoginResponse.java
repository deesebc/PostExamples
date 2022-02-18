package es.home.example.app.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 8080620636512014033L;
	private final String jwttoken;

}
