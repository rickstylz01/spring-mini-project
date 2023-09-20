package com.example.library.service;

import com.example.library.exception.InformationExistException;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private BookRepository bookRepository;

    //TODO add genre repository

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book bookObject) {
        Book book = bookRepository.findByTitle(bookObject.getTitle());
        if (book != null) {
            throw new InformationExistException("a book with the title: " + bookObject.getTitle() + ", already exists");
        } else {
            return bookRepository.save(bookObject);
        }
    }
}
