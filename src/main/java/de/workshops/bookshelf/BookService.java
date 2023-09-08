package de.workshops.bookshelf;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    public Book findBookByIsbn(String isbn) {
        return repository.getAllBooks().stream()
                .filter(book -> hasIsbn(book, isbn))
                .findFirst().orElseThrow(() -> new BookNotFoundException("ISBN: " + isbn));
    }

    public Book findBookByAuthor(String author) {
        return repository.getAllBooks().stream()
                .filter(book -> hasAuthor(book, author))
                .findFirst().orElseThrow();
    }

    public List<Book> findBookByAuthorAndIsbn(String author, String isbn) {
        return repository.getAllBooks().stream()
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
