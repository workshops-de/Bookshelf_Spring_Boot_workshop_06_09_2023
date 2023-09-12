package de.workshops.bookshelf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "book")
@NoArgsConstructor // required by JPA
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    @Getter
    private Long id;

    @Column(name = "title")
    @Getter
    @Setter
    private String title;

    @Column(name = "description", length = 1000)
    @Getter
    @Setter
    private String description;

    @Column(name = "author")
    @Getter
    @Setter
    private String author;

    @Column(name = "isbn")
    @Getter
    @Setter
    private String isbn;

    public Book(String title, String description, String author, String isbn) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
    }
}
