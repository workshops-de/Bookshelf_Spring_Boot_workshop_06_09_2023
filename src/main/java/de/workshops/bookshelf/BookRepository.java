package de.workshops.bookshelf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper mapper;
    private List<Book> books;

    public BookRepository(ResourceLoader resourceLoader, ObjectMapper mapper) {
        this.resourceLoader = resourceLoader;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init() throws Exception {
        Resource resource = this.resourceLoader.getResource("classpath:books.json");
        this.books = this.mapper.readValue(resource.getInputStream(), new TypeReference<>() {
        });
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
