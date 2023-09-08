package de.workshops.bookshelf;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BookRestControllerWebTest {

    @LocalServerPort
    int port;

    RestTemplate rest;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        rest = new RestTemplateBuilder().rootUri("http://localhost:" + port).build();
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
                when().
                    get("/book").
                then().
                    log().all().
                    statusCode(200).
                    body("title[0]", equalTo("Design Patterns"))
        ;
    }
}
