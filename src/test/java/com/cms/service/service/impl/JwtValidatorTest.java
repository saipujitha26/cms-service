package com.cms.service.service.impl;

import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cms.service.config.JwtConstant;
import com.cms.service.config.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidatorTest{

	private JwtValidatorForTest jwtValidatorForTest;
	
	@InjectMocks
    private com.cms.service.config.JwtValidator jwtValidator;
	@Mock
    private HttpServletRequest request;
	@Mock
    private HttpServletResponse response;
	@Mock
    private FilterChain filterChain;
	
 

    @BeforeEach
    public void setUp() {
        jwtValidator = new com.cms.service.config.JwtValidator();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);
        jwtValidatorForTest = new JwtValidatorForTest();
    }

    @Test
    public void testValidJwtToken() throws ServletException, IOException {
        String jwtToken = "validToken";
        when(request.getHeader(JwtConstant.JWT_HEADER)).thenReturn(jwtToken);
        String email = "test@example.com";
        when(JwtProvider.getEmailFromJwtToken(jwtToken)).thenReturn(email);

        jwtValidator.doFilterInternal(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        assert authentication.getPrincipal().equals(email);
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testInvalidJwtToken() throws ServletException, IOException {
        String jwtToken = "invalidToken";
        when(request.getHeader(JwtConstant.JWT_HEADER)).thenReturn(jwtToken);
        when(JwtProvider.getEmailFromJwtToken(jwtToken)).thenThrow(new Exception("Invalid token"));

        try {
            jwtValidator.doFilterInternal(request, response, filterChain);
        } catch (BadCredentialsException e) {
            assert e.getMessage().equals("invalid token");
        }

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testNoJwtToken() throws ServletException, IOException {
        when(request.getHeader(JwtConstant.JWT_HEADER)).thenReturn(null);

        jwtValidator.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }
}
