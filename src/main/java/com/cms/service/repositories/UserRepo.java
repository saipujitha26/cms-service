package com.cms.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cms.service.entities.User;

/**
 * Repository interface for performing CRUD operations on User entities.
 */
public interface UserRepo extends JpaRepository<User, Integer> {
    
    /**
     * Retrieves a user by their email.
     * 
     * @param email The email of the user to retrieve.
     * @return The user with the specified email, or null if not found.
     */
    public User findByEmail(String email);
    
    /**
     * Searches for users based on the given query string, which matches against the first name, last name, or email.
     * 
     * @param query The query string to search for.
     * @return A list of users matching the query.
     */
    @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);

}
