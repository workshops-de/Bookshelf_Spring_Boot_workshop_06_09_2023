package de.workshops.bookshelf.controller;

import lombok.Data;

@Data
public class BookSearchRequest {

    private String author;
    private String isbn;
}
