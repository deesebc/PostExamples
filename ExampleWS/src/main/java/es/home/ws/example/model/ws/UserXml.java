package es.home.ws.example.model.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import es.home.ws.example.model.User;

@XmlRootElement
public class UserXml implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String password;
	private boolean enabled;

	@XmlElement
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(final Integer userId) {
		this.userId = userId;
	}

	@XmlElement
	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@XmlElement
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public UserXml() {
		super();
	}

	public UserXml(final Integer userId) {
		super();
		this.userId = userId;
	}

	public UserXml(final Integer userId, final String username,
			final String password, final boolean enabled) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public static UserXml valueOf(final User user) {
		UserXml userXml = new UserXml();
		userXml.setEnabled(user.isEnabled());
		userXml.setPassword(user.getPassword());
		userXml.setUserId(user.getUserId());
		userXml.setUsername(user.getUsername());
		return userXml;
	}

}
