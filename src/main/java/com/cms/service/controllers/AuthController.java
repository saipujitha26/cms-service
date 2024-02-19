package com.cms.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.service.config.JwtProvider;
import com.cms.service.entities.User;
import com.cms.service.repositories.UserRepo;
import com.cms.service.request.LoginRequest;
import com.cms.service.response.AuthResponse;
import com.cms.service.services.CustomerUserDetailsService;
import com.cms.service.services.UserService;

import lombok.extern.slf4j.Slf4j;


/**
 * Controller class for handling user authentication operations.
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;

	
	/**
     * Endpoint for registering a new user.
     *
     * @param user The user details to register.
     * @return An AuthResponse containing the JWT token and a success message.
     * @throws Exception If the provided email is already associated with another account.
     */
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {

		LOGGER.info("START - SignUp action");
		User isExist = userRepo.findByEmail(user.getEmail());
		LOGGER.info("user Repository called..");
		if (isExist != null) {
			LOGGER.error("User Not Found in Repository.");
			throw new Exception("this email already used with another account");
		}

		User newUser = new User();

		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepo.save(newUser);

		LOGGER.info("User Saved Successfully.");
		LOGGER.info("START - Calling Authentication Method for JWT Token");
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		String token = JwtProvider.generateToken(authentication);
		LOGGER.info("END - Authentication Token Generated.");
		AuthResponse res = new AuthResponse(token, "Registered Success");

		return res;
	}

	
	/**
     * Endpoint for user login.
     *
     * @param loginRequest The login request containing user credentials.
     * @return An AuthResponse containing the JWT token and a success message upon successful login.
     */
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Login Success");

		return res;
	}

	
	/**
     * Authenticates a user based on provided email and password.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return The authentication object if successful.
     * @throws BadCredentialsException If the provided credentials are invalid.
     */
	private Authentication authenticate(String email, String password) {

		UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

		if (userDetails == null) {
			throw new BadCredentialsException("invalid username");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("wrong password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
