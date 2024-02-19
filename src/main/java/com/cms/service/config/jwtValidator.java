package com.cms.service.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Filter class for validating JWT (JSON Web Token) and setting authentication in the Spring Security context.
 */
public class JwtValidator extends OncePerRequestFilter {

	
	/**
     * Performs JWT validation and sets authentication in the Spring Security context for each HTTP request.
     *
     * @param request     The HTTP servlet request.
     * @param response    The HTTP servlet response.
     * @param filterChain The filter chain to execute.
     * @throws ServletException If an exception occurs during servlet processing.
     * @throws IOException      If an I/O exception occurs.
     */
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = request.getHeader(JwtConstant.JWT_HEADER);

		if (jwt != null) {
			try {

				// Extract email from JWT token
				String email = JwtProvider.getEmailFromJwtToken(jwt);

				// Create an empty list of authorities (no role-based access control)
				List<GrantedAuthority> authorities = new ArrayList<>();

				// Create authentication object with extracted email and empty authorities
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

				// Set authentication in the Spring Security context
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (Exception e) {

				// If any exception occurs during token validation, throw BadCredentialsException
				throw new BadCredentialsException("invalid token");

			}
		}

		// Proceed with the filter chain
		filterChain.doFilter(request, response);

	}

}
