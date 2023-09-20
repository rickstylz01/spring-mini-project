package com.example.library.service;

import com.example.library.exception.InformationExistException;
import com.example.library.exception.InformationNotFoundException;
import com.example.library.model.Book;
import com.example.library.model.User;
import com.example.library.repository.BookRepository;
import com.example.library.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    //TODO add genre repository

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static User getCurrentLoggedInUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    /**
     * Creates a new book based on the provided book object
     * @param bookObject
     * @return The newly created {@link Book} object
     */
    public Book createBook(Book bookObject) {
        Book book = bookRepository.findByTitle(bookObject.getTitle());
        if (book != null) {
            throw new InformationExistException("a book with the title: " + bookObject.getTitle() + ", already exists");
        } else {
            bookObject.setUsers(getCurrentLoggedInUser());
            return bookRepository.save(bookObject);
        }
    }

    public List<Book> getBooks() {
        List<Book> bookList = bookRepository.findByUserId(BookService.getCurrentLoggedInUser().getId());
        if(bookList.isEmpty()) {
            throw new InformationNotFoundException("no books found for user id: " + BookService.getCurrentLoggedInUser().getId() + " not found");
        } else {
            return bookList;
        }
    }
}
