package com.example.library.service;

import com.example.library.exception.InformationExistException;
import com.example.library.model.User;
import com.example.library.model.request.LoginRequest;
import com.example.library.repository.UserRepository;
import com.example.library.security.JWTUtils;
import com.example.library.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils,
                       @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Creates a new user in the system
     * @param userObject the user object to be created
     * @return The newly created {@link User} object
     * @throws InformationExistException If a user with the same email address already exists
     */
    public User createUser(User userObject) {
        if(!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            // sets a new password if there is no existing user with the same email
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address: " + userObject.getEmailAddress() + " already exists.");
        }
    }

    /**
     * Finds a user in the system by their email address
     * @param emailAddress The email address property to search by
     * @return The user object with the specific email address
     */
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }

    /**
     * Authenticate a user based on the provided login request and generates a JWT upon successful authentication
     * @param loginRequest The login request containing user credentials
     * @return An {@link Optional} containing the JWT token if authentication is successful, or empty if authentication fails
     */
    public Optional<String> loginUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmailAddress(), loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            return Optional.of(jwtUtils.generateJwtToken(myUserDetails));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
