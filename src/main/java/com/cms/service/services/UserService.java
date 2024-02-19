package com.cms.service.services;

import java.util.List;

import com.cms.service.entities.User;

/**
 * Interface defining the contract for user-related services.
 */
public interface UserService {

    /**
     * Register a new user.
     * 
     * @param user The user to register.
     * @return The registered user.
     */
    public User registerUser(User user);

    /**
     * Find a user by ID.
     * 
     * @param userId The ID of the user to find.
     * @return The found user.
     * @throws Exception if the user is not found.
     */
    public User findUserById(Integer userId) throws Exception;

    /**
     * Find a user by email.
     * 
     * @param email The email of the user to find.
     * @return The found user.
     */
    public User findUserByEmail(String email);

    /**
     * Update a user's information.
     * 
     * @param user   The updated user information.
     * @param userId The ID of the user to update.
     * @return The updated user.
     * @throws Exception if the user is not found.
     */
    public User updateUser(User user, Integer userId) throws Exception;

    /**
     * Search for users based on a query string.
     * 
     * @param query The query string to search for.
     * @return A list of users matching the query.
     */
    public List<User> searchUser(String query);
    
    /**
     * Find a user by JWT token.
     * 
     * @param jwt The JWT token associated with the user.
     * @return The user associated with the JWT token.
     */
    public User findUserByJwt(String jwt);
}
