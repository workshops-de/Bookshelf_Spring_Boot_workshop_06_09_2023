package de.workshops.bookshelf;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookRestControllerMvcTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getAllBooks_should_return_all_books() throws Exception {
        mvc.perform(get("/book"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].title", is("Clean Code")))
        ;
    }

    @Test
    void getAllBooks_should_return_array_with_books() throws Exception {
        MvcResult result = mvc.perform(get("/book"))
                .andDo(print())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(200);

        List<Book> books = objectMapper.readValue(response.getContentAsString(), new TypeReference<>() {
        });
        assertThat(books).hasSize(3);
        assertThat(books.get(1).getTitle()).isEqualTo("Clean Code");
    }
}
