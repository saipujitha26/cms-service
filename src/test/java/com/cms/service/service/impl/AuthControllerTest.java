package com.cms.service.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cms.service.controllers.AuthController;
import com.cms.service.entities.User;
import com.cms.service.repositories.UserRepo;
import com.cms.service.request.LoginRequest;
import com.cms.service.response.AuthResponse;
import com.cms.service.services.CustomerUserDetailsService;
import com.cms.service.services.UserService;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

	@InjectMocks
    private AuthController authController;
    @Mock
    private UserService userService;
    @Mock
    private UserRepo userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CustomerUserDetailsService customerUserDetailsService;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void testCreateUser_Success() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepo.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");

        AuthResponse response = authController.createUser(user);

        assertEquals("Registered Success", response.getMessage());
    }

    @Test
    public void testCreateUser_EmailAlreadyExists() {
        User user = new User();
        user.setEmail("existing@example.com");

        when(userRepo.findByEmail(user.getEmail())).thenReturn(user);

        try {
            authController.createUser(user);
        } catch (Exception e) {
            assertEquals("this email already used with another account", e.getMessage());
        }
    }

    @Test
    public void testSignin_Success() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        UserDetails userDetails = mock(UserDetails.class);
        when(customerUserDetailsService.loadUserByUsername(loginRequest.getEmail())).thenReturn(userDetails);
        when(passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())).thenReturn(true);

        AuthResponse response = authController.signin(loginRequest);

        assertEquals("Login Success", response.getMessage());
    }

    @Test
    public void testSignin_InvalidCredentials() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("invalid@example.com");
        loginRequest.setPassword("password");

        when(customerUserDetailsService.loadUserByUsername(loginRequest.getEmail())).thenReturn(null);

        try {
            authController.signin(loginRequest);
        } catch (BadCredentialsException e) {
            assertEquals("invalid username", e.getMessage());
        }
    }
}
