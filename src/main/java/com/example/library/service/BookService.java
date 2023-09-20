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
import java.util.Objects;
import java.util.Optional;

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
     * @param bookObject The book object to be created
     * @return The newly created {@link Book} object
     */
    public Book createBook(Book bookObject) {
        Book book = bookRepository.findByTitle(bookObject.getTitle());
        if (book != null) {
            throw new InformationExistException("a book with the title: " + bookObject.getTitle() + ", already exists");
        } else {
            bookObject.setUser(getCurrentLoggedInUser());
            return bookRepository.save(bookObject);
        }
    }

    /**
     * Retrieves a list of books associated with the currently logged-in User.
     * @return A list of {@link Book} objects belonging to the current user
     */
    public List<Book> getBooks() {
        List<Book> bookList = bookRepository.findByUserId(BookService.getCurrentLoggedInUser().getId());
        if(bookList.isEmpty()) {
            throw new InformationNotFoundException("no books found for user id: " + BookService.getCurrentLoggedInUser().getId() + " not found");
        } else {
            return bookList;
        }
    }

    public Optional<Book> getBook(Long bookId) {
        Book book = bookRepository.findByIdAndUserId(bookId, BookService.getCurrentLoggedInUser().getId());
        if (book == null) {
            throw new InformationNotFoundException("book with id: " + bookId + ", not found");
        } else {
            return Optional.of(book);
        }
    }

    /**
     * Compares the properties of two book objects to determine if they are equal
     * @param existingBook The existing book object to compare
     * @param newBook The new book object to compare
     * @return True if the book properties are equal, false otherwise.
     */
    private boolean areBookPropertiesEqual(Book existingBook, Book newBook) {
        return Objects.equals(existingBook.getTitle(), newBook.getTitle()) &&
                Objects.equals(existingBook.getAuthor(), newBook.getAuthor()) &&
                existingBook.getPrice() == newBook.getPrice() &&
                Objects.equals(existingBook.getGenres(), newBook.getGenres()) &&
                Objects.equals(existingBook.getUser(), newBook.getUser());
    }

    /**
     * Updates the properties of an existing book based on the provided book object
     * @param bookId The unique identifier of the book to be updated
     * @param bookObject The book object containing updated properties
     * @return The updated {@link Book} object if changes are made, or the existing book if no properties are modified
     */
    public Book updateBook(Long bookId, Book bookObject) {
        Book existingBook = bookRepository.findByIdAndUserId(bookId, getCurrentLoggedInUser().getId());
        if (existingBook == null) {
            throw new InformationNotFoundException("Book not found");
        }

        // Check if any property is different, if not, return the existing book
        if (areBookPropertiesEqual(existingBook, bookObject)) {
            throw new InformationExistException(("The book data is the same as the existing book."));
        }

        // Update only the properties that have changed
        if (!Objects.equals(existingBook.getTitle(), bookObject.getTitle())) {
            existingBook.setTitle(bookObject.getTitle());
        }

        if (!Objects.equals(existingBook.getAuthor(), bookObject.getAuthor())) {
            existingBook.setAuthor(bookObject.getAuthor());
        }

        if (existingBook.getPrice() != bookObject.getPrice()) {
            existingBook.setPrice(bookObject.getPrice());
        }

        if (!Objects.equals(existingBook.getGenres(), bookObject.getGenres())) {
            existingBook.setGenres(bookObject.getGenres());
        }

        // Save the updated book
        return bookRepository.save(existingBook);
    }

    /**
     * Deletes a book with the specified ID belonging to the current user.
     * @param bookId The unique identifier of the book to be deleted
     * @return An {@link Optional} containing the deleted {@link Book} object if found and deleted, or empty if the book does not exist
     */
    public Optional<Book> deleteBook(Long bookId) {
        Optional<Book> bookOptional = Optional.ofNullable(bookRepository.findByIdAndUserId(bookId, getCurrentLoggedInUser().getId()));
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(bookId);
            return bookOptional;
        } else {
            throw new InformationNotFoundException("book with id: " + bookId + ", not found" );
        }
    }
}
