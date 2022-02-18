package es.home.example.app.filter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

public class JWTUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 2945644630220594419L;

	public static JWTUserDetailsImpl build(final Long id, final String userName, final List<String> roles) {
		List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		return new JWTUserDetailsImpl(id, userName, null, authorities);
	}

	private String username;

	@Getter
	private Long id;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public JWTUserDetailsImpl(final long id, final String userName, final String password,
			final List<GrantedAuthority> authorities) {
		this.id = id;
		username = userName;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
