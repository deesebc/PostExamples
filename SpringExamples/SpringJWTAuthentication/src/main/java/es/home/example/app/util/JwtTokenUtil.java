package es.home.example.app.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenUtil {

	private Algorithm algorithmHS;
	private JWTVerifier verifier;

	@Value("${jwt.api.issuer}")
	private String loginApiIssuer;

	@Value("${jwt.api.jwtExpiration}")
	private int jwtExpiration;

	public String generateAccessToken(final User user, final List<String> roles) {
		return JWT.create().withIssuer(loginApiIssuer).withSubject(user.getUserName()).withIssuedAt(getIssuedAt())
				.withClaim("roles", roles).withExpiresAt(getExpireAt()).sign(getAlgorithmHS());
	}

	private Algorithm getAlgorithmHS() {
		if (algorithmHS == null) {
			try {
				File resource = new ClassPathResource("jwt-secret").getFile();
				String text = new String(Files.readAllBytes(resource.toPath()), Charset.defaultCharset());
				algorithmHS = Algorithm.HMAC512(text);
			} catch (IOException e) {
				// Invalid signature/claims
				log.error("Error getting expiration date: " + e.getMessage(), e);
			}
		}
		return algorithmHS;
	}

	public DecodedJWT getDecodedJWT(final String token) {
		DecodedJWT jwt = null;
		try {
			jwt = getVerifier().verify(token);
		} catch (JWTVerificationException e) {
			// Invalid signature/claims
			log.error("Error validating token JWT: " + e.getMessage(), e);
		}
		return jwt;
	}

	private Date getExpirationDate(final DecodedJWT verify) {
		return verify.getExpiresAt();
	}

	public Date getExpirationDate(final String token) {
		Date retorno = null;
		try {
			retorno = getExpirationDate(getVerifier().verify(token));
		} catch (JWTVerificationException e) {
			// Invalid signature/claims
			log.error("Error getting expiration date: " + e.getMessage(), e);
		}
		return retorno;
	}

	protected Date getExpireAt() {
		return Date.from(LocalDateTime.now().plusSeconds(jwtExpiration).atZone(ZoneId.systemDefault()).toInstant());
	}

	protected Date getIssuedAt() {
		return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	}

	public String getUsername(final DecodedJWT token) {
		return token.getSubject();
	}

	public String getUsername(final String token) {
		String retorno = null;
		try {
			retorno = getUsername(getVerifier().verify(token));
		} catch (JWTVerificationException e) {
			// Invalid signature/claims
			log.error("Error getting username: " + e.getMessage(), e);
		}
		return retorno;
	}

	private JWTVerifier getVerifier() {
		if (verifier == null) {
			verifier = JWT.require(getAlgorithmHS()).withIssuer(loginApiIssuer).build();
		}
		return verifier;
	}

	public boolean validate(final String token) {
		return getDecodedJWT(token) != null;
	}

}
