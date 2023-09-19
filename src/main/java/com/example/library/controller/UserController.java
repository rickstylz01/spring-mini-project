package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.model.request.LoginRequest;
import com.example.library.model.response.LoginResponse;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/auth/users") // http://localhost:9096/auth/users/
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    /**
     * Creates a new user based on the provided user object
     * @param userObject The user object to be created.
     * @return The newly created {@link User} object
     */
    @PostMapping(path = "/register/") // http://localhost:9096/auth/users/register/
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }

    /**
     * Logs in a user using the provided login request and returns a response containing a JWT
     * @param loginRequest The login request containing user credentials.
     * @return A {@link ResponseEntity} containing a {@link LoginResponse} with the JWT token if authentication is successful,
     *  *         or a response indicating authentication failure with a corresponding HTTP status code.
     */
    @PostMapping(path = "/login/") // http://localhost:9096/auth/users/login/
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<String> jwtToken = userService.loginUser(loginRequest);
        if (jwtToken.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse("Authentication failed"));
        }
    }
}
