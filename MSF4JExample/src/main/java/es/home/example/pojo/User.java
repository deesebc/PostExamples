package es.home.example.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends GenericEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;

	public User() {
		super();
	}

	public User(final Integer id, final String name, final String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Column(name = "NAME", length = 255)
	public String getName() {
		return name;
	}

	@Column(name = "PASSWORD", length = 255)
	public String getPassword() {
		return password;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}
