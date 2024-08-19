package com.ust.authorservice.repository;

import com.ust.authorservice.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        showSql = true,
        properties = {
                "spring.datasource.url=jdbc:h2:mem:testdb",
                "spring.datasource.driver-class-name=org.h2.Driver",
                "spring.datasource.username=sa",
                "spring.datasource.password=password",
                "logging.pattern.console=%msg%n"
        }
)
class AuthorJpaRepositoryTest {

    @Autowired
    AuthorJpaRepository authorRepository;

    @Test
    @DisplayName("Create a new Author")
    void testCreateAuthor() {
        // Given
        Author author = new Author("Jane Austen");
        // When
        Author savedAuthor = authorRepository.save(author);
        // Then
        assertNotNull(savedAuthor);
    }

    @Test
    @DisplayName("Find all authors by name")
    void testFindAllAuthorsByName() {
        // Given
        Author author = new Author("Jane Austen");
        authorRepository.save(author);
        // When
        var repoAuthor = authorRepository.findByName("Jane Austen");
        // Then
        assertNotNull(repoAuthor);
        assertEquals("Jane Austen", repoAuthor.getName());
    }

}