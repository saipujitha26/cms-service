package com.cms.service.request;

/**
 * A class representing a login request with email and password.
 */
public class LoginRequest {
    
    private String email;
    private String password;
    
    /**
     * Constructs an empty LoginRequest object.
     */
    public LoginRequest() {
        
    }

    /**
     * Constructs a LoginRequest object with the specified email and password.
     * 
     * @param email The email associated with the login request.
     * @param password The password associated with the login request.
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the email associated with the login request.
     * 
     * @return The email associated with the login request.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email associated with the login request.
     * 
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password associated with the login request.
     * 
     * @return The password associated with the login request.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password associated with the login request.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
