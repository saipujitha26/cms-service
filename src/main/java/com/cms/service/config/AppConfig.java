package com.cms.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import jakarta.servlet.http.HttpServletRequest;


/**
 * Configuration class for setting up application security and CORS (Cross-Origin Resource Sharing) policy.
 */
@Configuration
@EnableWebSecurity
public class AppConfig {

	
	/**
     * Configures security filters and session management for the application.
     *
     * @param http The HttpSecurity object to configure security settings.
     * @return The SecurityFilterChain configured for the application.
     * @throws Exception If an error occurs while configuring security.
     */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						Authorize -> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
				.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class).csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(CorsConfigurationSource()));

		return http.build();
	}

	
	/**
     * Configures CORS (Cross-Origin Resource Sharing) for the application.
     *
     * @return The CorsConfigurationSource configured for CORS policy.
     */
	private CorsConfigurationSource CorsConfigurationSource() {

		return new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);

				return cfg;
			}
		};
	}
	
	
	/**
     * Provides a PasswordEncoder bean for encoding passwords in the application.
     *
     * @return A BCryptPasswordEncoder bean for password encoding.
     */

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
}
