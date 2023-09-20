package com.example.library.seed;

import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SeedData implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder,
                    UserRepository userRepository,
                    BookRepository bookRepository) {
        //TODO add book and genre repositories after they're made
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("slickRick");
        user.setEmailAddress("rick@gmail.com");
        user.setPassword(passwordEncoder.encode("rick12345"));
        userRepository.save(user);

        Book book1 = new Book();
        book1.setTitle("The Alchemist");
        book1.setAuthor("Paulo Coelho");
        book1.setPrice(14.99F);
        book1.setUser(user);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Fifth Sun");
        book2.setAuthor("Camilla Townsend");
        book2.setPrice(16.99F);
        book2.setUser(user);
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setTitle("A Mind for Numbers");
        book3.setAuthor("Barbara Oakley");
        book3.setPrice(20.99F);
        book3.setUser(user);
        bookRepository.save(book3);
    }
}
