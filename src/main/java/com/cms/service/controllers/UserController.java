package com.cms.service.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.service.entities.User;
import com.cms.service.repositories.UserRepo;
import com.cms.service.services.UserService;

/**
 * Controller class for managing user-related operations.
 */
@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    /**
     * Retrieve all users.
     *
     * @return List<User> - List of all users
     */
    @GetMapping("/api/users")
    public List<User> getUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }

    /**
     * Retrieve a user by ID.
     *
     * @param id Integer - User ID
     * @return User - User object
     * @throws Exception if user is not found
     */
    @GetMapping("/api/users/{userId}")
    public User getUsersById(@PathVariable("userId") Integer id) throws Exception {
        User user = userService.findUserById(id);
        return user;
    }

    /**
     * Search users based on a query string.
     *
     * @param query String - Search query
     * @return List<User> - List of users matching the query
     */
    @GetMapping("/api/users/search")
    public List<User> searchUser(@RequestParam("query") String query) {
        List<User> users = userService.searchUser(query);
        return users;
    }

    /**
     * Update user information.
     *
     * @param jwt  String - JWT token for authentication
     * @param user User - User object with updated information
     * @return User - Updated user object
     * @throws Exception if user is not found or unauthorized
     */
    @PutMapping("/api/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user, reqUser.getId());
        return updatedUser;
    }

    /**
     * Delete a user by ID.
     *
     * @param userId Integer - User ID
     * @return String - Confirmation message
     * @throws Exception if user is not found
     */
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Integer userId) throws Exception {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User does not exist with ID: " + userId);
        }
        userRepo.delete(user.get());
        return "User deleted successfully with ID: " + userId;
    }

    /**
     * Retrieve user profile based on JWT token.
     *
     * @param jwt String - JWT token for authentication
     * @return User - User object representing the profile
     */
    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null); // Exclude password from response
        return user;
    }
}
