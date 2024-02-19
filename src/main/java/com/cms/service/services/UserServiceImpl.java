package com.cms.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.service.config.JwtProvider;
import com.cms.service.entities.User;
import com.cms.service.repositories.UserRepo;

/**
 * Implementation of the UserService interface providing user-related operations.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	
	/**
     * Register a new user.
     * 
     * @param user The user to register.
     * @return The registered user.
     */
	@Override
	public User registerUser(User user) {

		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());

		User savedUser = userRepo.save(newUser);

		return savedUser;
	}

	
	 /**
     * Find a user by ID.
     * 
     * @param userId The ID of the user to find.
     * @return The found user.
     * @throws Exception if the user is not found.
     */
	@Override
	public User findUserById(Integer userId) throws Exception {

		Optional<User> user = userRepo.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}

		throw new Exception("user not exist with userid " + userId);
	}

	
	 /**
     * Find a user by email.
     * 
     * @param email The email of the user to find.
     * @return The found user.
     */
	@Override
	public User findUserByEmail(String email) {
		User user = userRepo.findByEmail(email);
		return user;
	}

	

    /**
     * Update a user's information.
     * 
     * @param user   The updated user information.
     * @param userId The ID of the user to update.
     * @return The updated user.
     * @throws Exception if the user is not found.
     */
	@Override
	public User updateUser(User user, Integer userId) throws Exception {

		Optional<User> user1 = userRepo.findById(userId);

		if (user1.isEmpty()) {
			throw new Exception("user not exist with id " + userId);
		}

		User oldUser = user1.get();

		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}

		User updatedUser = userRepo.save(oldUser);

		return updatedUser;
	}

	 /**
     * Search for users based on a query string.
     * 
     * @param query The query string to search for.
     * @return A list of users matching the query.
     */
	@Override
	public List<User> searchUser(String query) {

		return userRepo.searchUser(query);
	}

	

    /**
     * Find a user by JWT token.
     * 
     * @param jwt The JWT token associated with the user.
     * @return The user associated with the JWT token.
     */
	@Override
	public User findUserByJwt(String jwt) {

		String email=JwtProvider.getEmailFromJwtToken(jwt);
		
		User user=userRepo.findByEmail(email);
		return user;
	}

}
