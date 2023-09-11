package de.workshops.bookshelf;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookRestController {

    private final BookService service;

    private final BookshelfProperties properties;

    public BookRestController(BookService service, BookshelfProperties properties) {
        this.service = service;
        this.properties = properties;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public Book getSingleBook(@PathVariable String isbn) throws BookNotFoundException {
        return service.findBookByIsbn(isbn);
    }

    @GetMapping(params = "author")
    public Book searchBookByAuthor(@RequestParam @NotBlank @Size(min = 3) String author) {
        return service.findBookByAuthor(author);
    }

    @PostMapping("/search")
    public List<Book> searchBooks(@RequestBody BookSearchRequest searchRequest) {
        String author = searchRequest.getAuthor();
        String isbn = searchRequest.getIsbn();
        return service.findBookByAuthorAndIsbn(author, isbn);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found - " + e.getMessage());
    }

    @GetMapping("/{isbn}/lookup")
    public String lookupIsbn(@PathVariable String isbn) {
        return String.format("%s does lookup for %s at %s",
                properties.getOwner(), isbn, properties.getIsbnLookup().getServerUri());
    }

/*
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleBookNotFoundException(BookNotFoundException e) {
        return "Book not found - " + e.getMessage();
    }
*/
}
