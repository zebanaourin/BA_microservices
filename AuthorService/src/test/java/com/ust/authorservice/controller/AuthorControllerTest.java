package com.ust.authorservice.controller;

import com.ust.authorservice.domain.Author;
import com.ust.authorservice.repository.AuthorRepository;
import com.ust.authorservice.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthorRepository authorRepository;

    Author author;

    @BeforeEach
    void setUp() {
        author = new Author(1L, "John Doe");
    }

    // GET /authors
    @Test
    @DisplayName("GET /authors - OK")
    void testGetAllAuthorsIsOK() throws Exception {
        when(authorRepository.findAll()).thenReturn(List.of(author));
        mockMvc.perform(get("/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
        verify(authorRepository, times(1)).findAll();
    }

    // GET /authors/{id}

}