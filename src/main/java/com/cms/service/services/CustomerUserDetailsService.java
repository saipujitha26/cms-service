package com.cms.service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cms.service.entities.User;
import com.cms.service.repositories.UserRepo;

/**
 * Service class implementing UserDetailsService to provide custom user details retrieval logic.
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    /**
     * Load user details by username.
     * 
     * @param username The username to load details for.
     * @return UserDetails object containing user details.
     * @throws UsernameNotFoundException if the username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Creating a list of granted authorities for the user (empty in this example)
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Constructing and returning a UserDetails object with the user's details
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), 
            user.getPassword(), 
            authorities
        );
    }
}
