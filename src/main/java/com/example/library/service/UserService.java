package com.example.library.service;

import com.example.library.exception.InformationExistException;
import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
            userObject.setPassword(passwordEncoder.encode(userObject.getEmailAddress()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address: " + userObject.getEmailAddress() + " already exists.");
        }
    }
}
