package de.workshops.bookshelf;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends ListCrudRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);

    Optional<Book> findByAuthor(String author);
}
