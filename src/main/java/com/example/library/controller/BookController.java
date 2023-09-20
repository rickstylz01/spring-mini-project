package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // http://localhost:9096/api
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "/books/") // http://localhost:9096/api/books/
    public Book createBook(@RequestBody Book bookObject) {
        return bookService.createBook(bookObject);
    }

    @GetMapping(path = "/books/") // http://localhost:9096/api/books/
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
}
