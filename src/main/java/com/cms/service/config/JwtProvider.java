package com.cms.service.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


/**
 * Utility class for generating and parsing JWT (JSON Web Token) tokens.
 */
public class JwtProvider {

	
	/**
     * The secret key used for JWT signing and validation.
     */
	private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

	
	/**
     * Generates a JWT token for the given authentication.
     *
     * @param auth The authentication object containing user details.
     * @return The JWT token generated for the authentication.
     */
	public static String generateToken(Authentication auth) {
		
		String jwt=Jwts.builder()
				.setIssuer("Lee").setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.claim("email", auth.getName())
				.signWith(key)
				.compact();
		
		return jwt;
	}

	
	/**
     * Extracts the email from the provided JWT token.
     *
     * @param jwt The JWT token from which to extract the email.
     * @return The email extracted from the JWT token.
     */
	public static String getEmailFromJwtToken(String jwt) {
		
		jwt=jwt.substring(7);
		
		Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String email=String.valueOf(claims.get("email"));
		
		return email;
		
	}
}
