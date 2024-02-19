package com.cms.service.response;

/**
 * A class representing an authentication response containing a token and a message.
 */
public class AuthResponse {
    
    private String token;
    private String message;
    
    /**
     * Constructs an empty AuthResponse object.
     */
    public AuthResponse() {
        
    }

    /**
     * Constructs an AuthResponse object with the specified token and message.
     * 
     * @param token The authentication token.
     * @param message The message associated with the authentication response.
     */
    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    /**
     * Retrieves the authentication token.
     * 
     * @return The authentication token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the authentication token.
     * 
     * @param token The authentication token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Retrieves the message associated with the authentication response.
     * 
     * @return The message associated with the authentication response.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message associated with the authentication response.
     * 
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
