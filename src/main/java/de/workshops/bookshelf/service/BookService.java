package de.workshops.bookshelf.service;

import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.domain.BookNotFoundException;
import de.workshops.bookshelf.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book findBookByIsbn(String isbn) {
        return repository.findBookByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("ISBN: " + isbn));
    }

    public Book findBookByAuthor(String author) {
        return repository.findByAuthor(author)
                .orElseThrow();
    }

    public List<Book> findBookByAuthorAndIsbn(String author, String isbn) {
        return repository.findAll().stream()
                .filter(book -> author == null || hasAuthor(book, author))
                .filter(book -> isbn == null || hasIsbn(book, isbn))
                .toList();
    }

    private boolean hasIsbn(Book book, String isbn) {
        return book.getIsbn().equals(isbn);
    }

    private boolean hasAuthor(Book book, String author) {
        return book.getAuthor().contains(author);
    }
}
