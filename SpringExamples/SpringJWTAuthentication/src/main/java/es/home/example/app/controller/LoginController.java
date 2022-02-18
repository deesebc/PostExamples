package es.home.example.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.home.example.app.filter.JWTUserDetailsImpl;
import es.home.example.app.pojo.LoginRequest;
import es.home.example.app.pojo.LoginResponse;
import es.home.example.app.service.MockDetailsService;
import es.home.example.app.util.JwtTokenUtil;

@RestController
public class LoginController {

	@Autowired
	MockDetailsService mockDetailsService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	private ResponseEntity<LoginResponse> getLoginResponseFromUserDetails(final JWTUserDetailsImpl userDetails) {
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		String token = jwtTokenUtil.generateAccessToken(userDetails.getId(), userDetails.getUsername(), roles);

		return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest request) {
		JWTUserDetailsImpl userDetails = (JWTUserDetailsImpl) mockDetailsService
				.loadUserByUsername(request.getUserName());

		return getLoginResponseFromUserDetails(userDetails);
	}

	@PostMapping("/refresh-token")
	public ResponseEntity<LoginResponse> refreshToken(@RequestBody final String token) {
		JWTUserDetailsImpl userDetails = (JWTUserDetailsImpl) jwtTokenUtil.getUserDetails(token);

		return getLoginResponseFromUserDetails(userDetails);
	}

}
