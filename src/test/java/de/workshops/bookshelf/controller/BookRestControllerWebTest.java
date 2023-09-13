package de.workshops.bookshelf.controller;

import de.workshops.bookshelf.domain.Book;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Sql(executionPhase = BEFORE_TEST_METHOD, statements = "INSERT INTO bookshelf_user (username, password, role) VALUES ('unittest', '$2a$10$xHn16zGDgleKRYfHDm3Fj.XwyXihDIrAcNe24rocXk1qOyCIaYxma', 'ROLE_USER')")
@Sql(executionPhase = AFTER_TEST_METHOD, statements = "DELETE FROM bookshelf_user WHERE username = 'unittest'")
class BookRestControllerWebTest {

    @LocalServerPort
    int port;

    RestTemplate rest;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        rest = new RestTemplateBuilder().rootUri("http://localhost:" + port).basicAuthentication("unittest", "test").build();
    }

    @Test
    void with_RestTemplate() {
        ResponseEntity<Book[]> books = rest.getForEntity("/book", Book[].class);
        assertThat(books.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(books.getBody()).hasSize(3);
    }

    @Test
    void with_RestAssured() {
        RestAssured.
                given().
                    log().all().
                    auth().basic("unittest", "test").
                when().
                    get("/book").
                then().
                    log().all().
                    statusCode(200).
                    body("title[0]", equalTo("Design Patterns"))
        ;
    }
}
