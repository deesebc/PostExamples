package es.home.ws.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "users", catalog = "almacen")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String password;
	private boolean enabled;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", unique = true, nullable = false)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

	@Column(name = "USERNAME", length = 45)
	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", length = 45)
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Column(name = "ENABLED")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public User() {
		super();
	}

	public User(final Integer userId) {
		super();
		this.userId = userId;
	}

	public User(final Integer userId, final String username,
			final String password, final boolean enabled) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

}
