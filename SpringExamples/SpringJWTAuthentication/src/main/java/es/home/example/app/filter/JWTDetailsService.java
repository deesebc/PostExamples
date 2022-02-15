package es.home.example.app.filter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JWTDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(final String token) throws UsernameNotFoundException {
		return JWTUserDetailsImpl.build(1L, "username", null);
	}

}
