package es.home.example.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidence {

	private String server;
	private String env;
	private String level;
	private String info;

}
