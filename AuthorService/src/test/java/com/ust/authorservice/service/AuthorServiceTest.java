package com.ust.authorservice.service;

import com.ust.authorservice.domain.Author;
import com.ust.authorservice.repository.AuthorJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceTest {

    @Mock
    AuthorJpaRepository authorRepository;

    @InjectMocks
    AuthorService authorService;

    Author validAuthor, authorWithNoId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validAuthor = new Author(1L,"Enid Blyton");
        authorWithNoId = new Author(null, "Enid Blyton");
    }

    // Test saveAuthor when author is not present in db and
    // author id is not null
    @Test
    @DisplayName("saveAuthor() when author is not present in db and author id is not null")
    void testSaveAuthorWhenAuthorIsNotPresentAndAuthorIdIsNotNull() {
        // Mocking the save method of authorRepository
        when(authorRepository.save(validAuthor)).thenReturn(validAuthor);
        var savedAuthor = authorService.saveAuthor(validAuthor);
        verify(authorRepository, times(1)).save(validAuthor);
        assertEquals(validAuthor.getName(), savedAuthor.getName());
    }

    // Test saveAuthor when author is present in the db
    @Test
    @DisplayName("saveAuthor() when author is present in db")
    void testSaveAuthorWhenAuthorIsPresent() {
        // Mocking the findByName method of authorRepository
        when(authorRepository.findByName("Enid Blyton")).thenReturn(validAuthor);
        // Test the saveAuthor method
        var ex = assertThrows(IllegalArgumentException.class,
                () -> authorService.saveAuthor(validAuthor));
        verify(authorRepository, times(1)).findByName("Enid Blyton");
        assertEquals("Author with name Enid Blyton already exists", ex.getMessage());
    }

    // Test findById when author is present in db
    @Test
    @DisplayName("findAuthorById() when author is present in db")
    void testFindAuthorByIdWhenAuthorIsPresent() {
        // Mocking the findById method of authorRepository
        when(authorRepository.findById(1L)).thenReturn(Optional.of(validAuthor));
        var foundAuthor = authorService.findAuthorById(1L);
        verify(authorRepository, times(1)).findById(1L);
        assertNotNull(foundAuthor);
        assertEquals(validAuthor.getName(), foundAuthor.getName());
    }

    // Test finaALl when authors are present in db
    @Test
    @DisplayName("findAllAuthors() when authors are present in db")
    void testFindAllAuthorsWhenAuthorsArePresent() {
        // Mocking the findAll method of authorRepository
        when(authorRepository.findAll()).thenReturn(List.of(validAuthor));
        var authors = authorService.findAllAuthors();
        verify(authorRepository, times(1)).findAll();
        assertNotNull(authors);
        assertEquals(1, List.of(authors).size());
    }
}