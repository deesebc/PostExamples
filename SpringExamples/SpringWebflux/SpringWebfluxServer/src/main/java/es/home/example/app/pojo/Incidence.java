package es.home.example.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Incidence {

	private String env;
	private String server;
	private String level;
	private String info;

}
