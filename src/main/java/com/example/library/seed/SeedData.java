package com.example.library.seed;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder,
                    UserRepository userRepository) {
        //TODO add book and genre repositories after they're made
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("slickRick");
        user.setEmailAddress("rick@gmail.com");
        user.setPassword(passwordEncoder.encode("rick12345"));
        userRepository.save(user);
    }
}
