package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(path = "/books/{bookId}") // http://localhost:9096/api/books/1/
    public Optional<Book> getBook(@PathVariable(value = "bookId") Long bookId) {
        return bookService.getBook(bookId);
    }

    @PutMapping(path = "/books/{bookId}") // http://localhost:9096/api/books/1/
    public Book updateBook(@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookObject) {
        return bookService.updateBook(bookId, bookObject);
    }

    @DeleteMapping(path = "/books/{bookId}") // http://localhost:9096/api/books/1/
    public Optional<Book> deleteBook(@PathVariable(value = "bookId") Long bookId) {
        return bookService.deleteBook(bookId);
    }
}
