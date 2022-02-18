package es.home.example.app.service;

import java.util.Arrays;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import es.home.example.app.filter.JWTUserDetailsImpl;

@Service
public class MockDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(final String username) {
		return JWTUserDetailsImpl.build(1L, "userName", Arrays.asList("user", "admin"));
	}
}
