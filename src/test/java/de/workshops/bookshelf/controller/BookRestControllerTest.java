package de.workshops.bookshelf.controller;

import de.workshops.bookshelf.domain.Book;
import de.workshops.bookshelf.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookRestControllerTest {

    @MockBean
    BookService bookService;

    @Autowired
    BookRestController bookRestController;

    List<Book> knownBooks = List.of(
            new Book("Clean Code", "A book about clean code", "Uncle Bob", "12345"),
            new Book("Spring Boot 3", "Build nice applications fast", "Someone", "54321"),
            new Book("IntelliJ Productivity", "Program code very fast", "Also Someone", "98765")
    );

    @Test
    void getAllBooks_should_return_all_known_books() {
        when(bookService.getAllBooks()).thenReturn(knownBooks);

        List<Book> books = bookRestController.getAllBooks();
        assertThat(books).isEqualTo(knownBooks);
    }
}