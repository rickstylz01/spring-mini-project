package com.example.library.seed;

import com.example.library.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public SeedData(
            @Lazy PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            BookRepository bookRepository,
            GenreRepository genreRepository
            ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("SlickRick");
        user.setEmailAddress("rickmaya@gmail.com");
        user.setPassword(passwordEncoder.encode("rick12345"));
        userRepository.save(user);
    }

}
